package com.example.gymServer.controllers;

import com.example.gymServer.dto.TrainingGoalDTO;
import com.example.gymServer.services.TrainingGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/training-goals")
@RequiredArgsConstructor
public class TrainingGoalController {

    private final TrainingGoalService trainingGoalService;

    @GetMapping
    public ResponseEntity<List<TrainingGoalDTO>> getAllTrainingGoals() {
        return ResponseEntity.ok(trainingGoalService.getAllTrainingGoals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingGoalDTO> getTrainingGoalById(@PathVariable Long id) {
        return trainingGoalService.getTrainingGoalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TrainingGoalDTO> createTrainingGoal(@RequestBody TrainingGoalDTO trainingGoalDTO) {
        return ResponseEntity.ok(trainingGoalService.createTrainingGoal(trainingGoalDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingGoalDTO> updateTrainingGoal(
            @PathVariable Long id,
            @RequestBody TrainingGoalDTO updatedTrainingGoalDTO) {
        return ResponseEntity.ok(trainingGoalService.updateTrainingGoal(id, updatedTrainingGoalDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingGoal(@PathVariable Long id) {
        trainingGoalService.deleteTrainingGoal(id);
        return ResponseEntity.noContent().build();
    }
}
