package com.example.gymServer.subscription;

import com.example.gymServer.authorization.user.User;
import com.example.gymServer.authorization.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public Optional<Subscription> getSubscriptionByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public Subscription createOrUpdateSubscription(Integer userId, LocalDate startDate, LocalDate endDate) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Subscription subscription = subscriptionRepository.findByUserId(Long.valueOf(userId)).orElse(new Subscription());
        subscription.setUser(user);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);
        return subscriptionRepository.save(subscription);
    }

    public void extendSubscription(Long userId, LocalDate newEndDate) {
        Subscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        subscription.setEndDate(newEndDate);
        subscriptionRepository.save(subscription);
    }
}
