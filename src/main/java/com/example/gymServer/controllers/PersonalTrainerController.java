package com.example.gymServer.controllers;

import com.example.gymServer.dto.PersonalTrainerDTO;
import com.example.gymServer.services.PersonalTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personal-trainers")
public class PersonalTrainerController {

    private final PersonalTrainerService personalTrainerService;

    @Autowired
    public PersonalTrainerController(PersonalTrainerService personalTrainerService) {
        this.personalTrainerService = personalTrainerService;
    }

    @GetMapping
    public ResponseEntity<List<PersonalTrainerDTO>> getAllPersonalTrainers() {
        List<PersonalTrainerDTO> trainers = personalTrainerService.getAllPersonalTrainers();
        return ResponseEntity.ok(trainers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalTrainerDTO> getPersonalTrainerById(@PathVariable Long id) {
        PersonalTrainerDTO trainer = personalTrainerService.getPersonalTrainerById(id);
        return trainer != null ? ResponseEntity.ok(trainer) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PersonalTrainerDTO> createPersonalTrainer(@RequestBody PersonalTrainerDTO personalTrainerDTO) {
        PersonalTrainerDTO createdTrainer = personalTrainerService.savePersonalTrainer(personalTrainerDTO);
        return ResponseEntity.ok(createdTrainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalTrainer(@PathVariable Long id) {
        personalTrainerService.deletePersonalTrainer(id);
        return ResponseEntity.noContent().build();
    }
}
