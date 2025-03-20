package com.example.gymServer.services;

import com.example.gymServer.authorization.user.User;
import com.example.gymServer.authorization.user.UserRepository;
import com.example.gymServer.dto.TrainingGoalDTO;
import com.example.gymServer.mapper.TrainingGoalMapper;
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
    private final TrainingGoalMapper trainingGoalMapper;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<TrainingGoalDTO> getAllTrainingGoals() {
        User user = getCurrentUser();
        List<TrainingGoal> trainingGoals = trainingGoalRepository.findByUserId(Long.valueOf(user.getId()));
        return trainingGoalMapper.toTrainingGoalDTOs(trainingGoals);
    }

    public Optional<TrainingGoalDTO> getTrainingGoalById(Long id) {
        User user = getCurrentUser();
        return trainingGoalRepository.findById(id)
                .filter(goal -> goal.getUser().getId().equals(user.getId()))
                .map(trainingGoalMapper::toTrainingGoalDTO);
    }

    public TrainingGoalDTO createTrainingGoal(TrainingGoalDTO trainingGoalDTO) {
        User user = getCurrentUser();
        TrainingGoal trainingGoal = trainingGoalMapper.toTrainingGoal(trainingGoalDTO);
        trainingGoal.setUser(user);
        TrainingGoal savedGoal = trainingGoalRepository.save(trainingGoal);
        return trainingGoalMapper.toTrainingGoalDTO(savedGoal);
    }

    public TrainingGoalDTO updateTrainingGoal(Long id, TrainingGoalDTO updatedTrainingGoalDTO) {
        User user = getCurrentUser();
        return trainingGoalRepository.findById(id)
                .filter(goal -> goal.getUser().getId().equals(user.getId()))
                .map(trainingGoal -> {
                    trainingGoal.setContent(updatedTrainingGoalDTO.getContent());
                    trainingGoal.setFinished(updatedTrainingGoalDTO.isFinished());
                    TrainingGoal updatedGoal = trainingGoalRepository.save(trainingGoal);
                    return trainingGoalMapper.toTrainingGoalDTO(updatedGoal);
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
