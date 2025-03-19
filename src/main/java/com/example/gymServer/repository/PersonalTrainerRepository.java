package com.example.gymServer.repository;

import com.example.gymServer.models.PersonalTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Long> {
}
