package com.example.gymServer.services;


import com.example.gymServer.authorization.user.User;
import com.example.gymServer.authorization.user.UserRepository;
import com.example.gymServer.models.TrainingGoal;
import com.example.gymServer.repository.TrainingGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingGoalService {

    private final TrainingGoalRepository trainingGoalRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<TrainingGoal> getAllTrainingGoals() {
        User user = getCurrentUser();
        return trainingGoalRepository.findByUserId(Long.valueOf(user.getId()));
    }

    public Optional<TrainingGoal> getTrainingGoalById(Long id) {
        User user = getCurrentUser();
        return trainingGoalRepository.findById(id)
                .filter(goal -> goal.getUser().getId().equals(user.getId()));
    }

    public TrainingGoal createTrainingGoal(TrainingGoal trainingGoal) {
        User user = getCurrentUser();
        trainingGoal.setUser(user);
        return trainingGoalRepository.save(trainingGoal);
    }

    public TrainingGoal updateTrainingGoal(Long id, TrainingGoal updatedTrainingGoal) {
        User user = getCurrentUser();
        return trainingGoalRepository.findById(id)
                .filter(goal -> goal.getUser().getId().equals(user.getId()))
                .map(trainingGoal -> {
                    trainingGoal.setContent(updatedTrainingGoal.getContent());
                    trainingGoal.setFinished(updatedTrainingGoal.isFinished());
                    return trainingGoalRepository.save(trainingGoal);
                })
                .orElseThrow(() -> new RuntimeException("TrainingGoal not found or you do not have access to it"));
    }

    public void deleteTrainingGoal(Long id) {
        User user = getCurrentUser();
        trainingGoalRepository.findById(id)
                .filter(goal -> goal.getUser().getId().equals(user.getId()))
                .ifPresent(trainingGoalRepository::delete);
    }
}
