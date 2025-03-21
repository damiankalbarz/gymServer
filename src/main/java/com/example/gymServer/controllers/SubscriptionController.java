package com.example.gymServer.controllers;

import com.example.gymServer.dto.SubscriptionDTO;
import com.example.gymServer.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<SubscriptionDTO> getSubscriptionByUserId(@PathVariable Long userId) {
        Optional<SubscriptionDTO> subscription = subscriptionService.getSubscriptionByUserId(userId);
        return subscription.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<SubscriptionDTO> createOrUpdateSubscription(
            @PathVariable Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        SubscriptionDTO subscription = subscriptionService.createOrUpdateSubscription(userId, startDate, endDate);
        return ResponseEntity.ok(subscription);
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
