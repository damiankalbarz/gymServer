package com.example.gymServer.controllers;

import com.example.gymServer.models.Subscription;
import com.example.gymServer.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Subscription> getSubscriptionByUserId(@PathVariable Integer userId) {
        Optional<Subscription> subscription = subscriptionService.getSubscriptionByUserId(Long.valueOf(userId));
        return subscription.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user/{userId}")
    public Subscription createOrUpdateSubscription(@PathVariable Integer userId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return subscriptionService.createOrUpdateSubscription(userId, startDate, endDate);
    }

    @PutMapping("/user/{userId}/extend")
    public ResponseEntity<Void> extendSubscription(@PathVariable Long userId, @RequestParam LocalDate newEndDate) {
        try {
            subscriptionService.extendSubscription(userId, newEndDate);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
