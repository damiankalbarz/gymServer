package com.example.gymServer.mapper;

import com.example.gymServer.dto.SubscriptionDTO;
import com.example.gymServer.models.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(source = "user.id", target = "userId")
    SubscriptionDTO toSubscriptionDTO(Subscription subscription);

    @Mapping(source = "userId", target = "user.id")
    Subscription toSubscription(SubscriptionDTO subscriptionDTO);
}
