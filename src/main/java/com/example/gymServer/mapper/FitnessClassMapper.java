package com.example.gymServer.mapper;

import com.example.gymServer.dto.FitnessClassDTO;
import com.example.gymServer.models.FitnessClass;
import com.example.gymServer.authorization.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FitnessClassMapper {

    @Mapping(source = "enrolledUsers", target = "enrolledUserIds", qualifiedByName = "mapEnrolledUsersToIds")
    FitnessClassDTO toFitnessClassDTO(FitnessClass fitnessClass);

    @Mapping(source = "enrolledUserIds", target = "enrolledUsers", qualifiedByName = "mapIdsToEnrolledUsers")
    FitnessClass toFitnessClass(FitnessClassDTO fitnessClassDTO);

    List<FitnessClassDTO> toFitnessClassDTOs(List<FitnessClass> fitnessClasses);

    List<FitnessClass> toFitnessClasses(List<FitnessClassDTO> fitnessClassDTOs);

    @Named("mapEnrolledUsersToIds")
    default List<Long> mapEnrolledUsersToIds(List<User> users) {
        return users != null ? users.stream()
                .map(user -> user.getId().longValue())
                .collect(Collectors.toList()) : null;
    }

    @Named("mapIdsToEnrolledUsers")
    default List<User> mapIdsToEnrolledUsers(List<Long> userIds) {
        return userIds != null ? userIds.stream().map(id -> {
            User user = new User();
            user.setId(id.intValue());
            return user;
        }).collect(Collectors.toList()) : null;
    }
}
