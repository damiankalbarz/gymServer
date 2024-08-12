package com.example.gymServer.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


import java.time.LocalTime;

@Data
@Entity
public class FitnessClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long personalTrainerId;

    private LocalTime startTime;

    private LocalTime endTime;

    private int maxUsers;

    private String dayOfWeek;

    private String className;

}
