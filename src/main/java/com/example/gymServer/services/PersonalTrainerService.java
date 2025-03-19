package com.example.gymServer.services;

import com.example.gymServer.models.PersonalTrainer;
import com.example.gymServer.repository.PersonalTrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalTrainerService {

    @Autowired
    private PersonalTrainerRepository personalTrainerRepository;

    public List<PersonalTrainer> getAllPersonalTrainers() {
        return personalTrainerRepository.findAll();
    }

    public PersonalTrainer getPersonalTrainerById(Long id) {
        return personalTrainerRepository.findById(id).orElse(null);
    }

    public PersonalTrainer savePersonalTrainer(PersonalTrainer personalTrainer) {
        return personalTrainerRepository.save(personalTrainer);
    }

    public void deletePersonalTrainer(Long id) {
        personalTrainerRepository.deleteById(id);
    }
}
