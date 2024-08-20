package com.example.gymServer.TrainingGoal;

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
    public ResponseEntity<List<TrainingGoal>> getAllTrainingGoals() {
        return ResponseEntity.ok(trainingGoalService.getAllTrainingGoals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingGoal> getTrainingGoalById(@PathVariable Long id) {
        return trainingGoalService.getTrainingGoalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TrainingGoal> createTrainingGoal(@RequestBody TrainingGoal trainingGoal) {
        return ResponseEntity.ok(trainingGoalService.createTrainingGoal(trainingGoal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingGoal> updateTrainingGoal(
            @PathVariable Long id,
            @RequestBody TrainingGoal updatedTrainingGoal) {
        return ResponseEntity.ok(trainingGoalService.updateTrainingGoal(id, updatedTrainingGoal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingGoal(@PathVariable Long id) {
        trainingGoalService.deleteTrainingGoal(id);
        return ResponseEntity.noContent().build();
    }
}
