package com.example.gymServer.services;

import com.example.gymServer.authorization.user.User;
import com.example.gymServer.authorization.user.UserRepository;
import com.example.gymServer.dto.SubscriptionDTO;
import com.example.gymServer.mapper.SubscriptionMapper;
import com.example.gymServer.models.Subscription;
import com.example.gymServer.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final TwilioService twilioService;

    public Optional<SubscriptionDTO> getSubscriptionByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId)
                .map(subscriptionMapper::toSubscriptionDTO);
    }

    public SubscriptionDTO createOrUpdateSubscription(Long userId, LocalDate startDate, LocalDate endDate) {
        User user = userRepository.findById(userId.intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Subscription subscription = subscriptionRepository.findByUserId(userId)
                .orElse(new Subscription());

        subscription.setUser(user);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);

        twilioService.sendSMS(String.valueOf(endDate), user.getPhoneNumber());
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        return subscriptionMapper.toSubscriptionDTO(savedSubscription);
    }

    public void extendSubscription(Long userId, LocalDate newEndDate) {
        Subscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        subscription.setEndDate(newEndDate);
        subscriptionRepository.save(subscription);
    }
}
