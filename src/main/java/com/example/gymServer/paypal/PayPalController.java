package com.example.gymServer.paypal;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/paypal")
public class PayPalController {

    @Autowired
    private PayPalService service;

    private static final String SUCCESS_URL = "http://localhost:8080/paypal/success";
    private static final String CANCEL_URL = "http://localhost:8080/paypal/cancel";

    @GetMapping("/pay")
    public String payment() {
        try {
            Payment payment = service.createPayment(
                    10.00,
                    "USD",
                    "paypal",
                    "sale",
                    "Test Payment",
                    CANCEL_URL,
                    SUCCESS_URL
            );
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
