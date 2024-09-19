package com.example.gymServer.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TwilioService {

    @Autowired
    private TwilioConfig.TwilioInitializer twilioInitializer;

    public void sendSMS(String date,String phoneNumber){
        String messageContent = "Ważność karnetu została przedłużona do "+date;
        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioInitializer.getFromPhoneNumber()),
                messageContent
        ).create();
    }

}
