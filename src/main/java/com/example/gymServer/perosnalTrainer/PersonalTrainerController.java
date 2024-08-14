package com.example.gymServer.perosnalTrainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personal-trainer")
public class PersonalTrainerController {

    @Autowired
    private PersonalTrainerService personalTrainerService;

    @GetMapping
    public List<PersonalTrainer> getAllPersonalTrainers() {
        return personalTrainerService.getAllPersonalTrainers();
    }

    @GetMapping("/{id}")
    public PersonalTrainer getPersonalTrainerById(@PathVariable Long id) {
        return personalTrainerService.getPersonalTrainerById(id);
    }

    @PostMapping
    public PersonalTrainer createPersonalTrainer(@RequestBody PersonalTrainer personalTrainer) {
        return personalTrainerService.savePersonalTrainer(personalTrainer);
    }

    @DeleteMapping("/{id}")
    public void deletePersonalTrainer(@PathVariable Long id) {
        personalTrainerService.deletePersonalTrainer(id);
    }
}
