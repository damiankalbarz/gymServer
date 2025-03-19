package com.example.gymServer.repository;

import com.example.gymServer.models.FitnessClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessClassRepository extends JpaRepository<FitnessClass, Long> {
}
