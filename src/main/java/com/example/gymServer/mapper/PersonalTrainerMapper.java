package com.example.gymServer.mapper;

import com.example.gymServer.dto.PersonalTrainerDTO;
import com.example.gymServer.models.PersonalTrainer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonalTrainerMapper {
    PersonalTrainerDTO toPersonalTrainerDTO(PersonalTrainer personalTrainer);
    PersonalTrainer toPersonalTrainer(PersonalTrainerDTO personalTrainerDTO);
    List<PersonalTrainerDTO> toPersonalTrainerDTOs(List<PersonalTrainer> personalTrainers);
}
