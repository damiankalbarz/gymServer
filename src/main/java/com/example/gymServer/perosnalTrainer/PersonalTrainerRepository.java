package com.example.gymServer.perosnalTrainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Long> {
}
