package com.example.gymServer.services;

import com.example.gymServer.dto.PersonalTrainerDTO;
import com.example.gymServer.mapper.PersonalTrainerMapper;
import com.example.gymServer.models.PersonalTrainer;
import com.example.gymServer.repository.PersonalTrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalTrainerService {

    private final PersonalTrainerRepository personalTrainerRepository;
    private final PersonalTrainerMapper personalTrainerMapper;

    @Autowired
    public PersonalTrainerService(PersonalTrainerRepository personalTrainerRepository, PersonalTrainerMapper personalTrainerMapper) {
        this.personalTrainerRepository = personalTrainerRepository;
        this.personalTrainerMapper = personalTrainerMapper;
    }

    public List<PersonalTrainerDTO> getAllPersonalTrainers() {
        List<PersonalTrainer> trainers = personalTrainerRepository.findAll();
        return personalTrainerMapper.toPersonalTrainerDTOs(trainers);
    }

    public PersonalTrainerDTO getPersonalTrainerById(Long id) {
        PersonalTrainer trainer = personalTrainerRepository.findById(id).orElse(null);
        return trainer != null ? personalTrainerMapper.toPersonalTrainerDTO(trainer) : null;
    }

    public PersonalTrainerDTO savePersonalTrainer(PersonalTrainerDTO personalTrainerDTO) {
        PersonalTrainer trainer = personalTrainerMapper.toPersonalTrainer(personalTrainerDTO);
        PersonalTrainer savedTrainer = personalTrainerRepository.save(trainer);
        return personalTrainerMapper.toPersonalTrainerDTO(savedTrainer);
    }

    public void deletePersonalTrainer(Long id) {
        personalTrainerRepository.deleteById(id);
    }
}
