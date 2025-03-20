package com.example.gymServer.mapper;

import com.example.gymServer.dto.TrainingGoalDTO;
import com.example.gymServer.models.TrainingGoal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainingGoalMapper {

    TrainingGoalMapper INSTANCE = Mappers.getMapper(TrainingGoalMapper.class);

    @Mapping(source = "user.id", target = "userId")
    TrainingGoalDTO toTrainingGoalDTO(TrainingGoal trainingGoal);

    @Mapping(source = "userId", target = "user.id")
    TrainingGoal toTrainingGoal(TrainingGoalDTO trainingGoalDTO);

    List<TrainingGoalDTO> toTrainingGoalDTOs(List<TrainingGoal> trainingGoals);

    List<TrainingGoal> toTrainingGoals(List<TrainingGoalDTO> trainingGoalDTOs);
}
