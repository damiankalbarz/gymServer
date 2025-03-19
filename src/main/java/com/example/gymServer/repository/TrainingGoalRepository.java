package com.example.gymServer.repository;

import com.example.gymServer.models.TrainingGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainingGoalRepository extends JpaRepository<TrainingGoal, Long> {
    List<TrainingGoal> findByUserId(Long userId);
}
