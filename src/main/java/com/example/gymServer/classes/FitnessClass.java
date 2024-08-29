package com.example.gymServer.classes;

import com.example.gymServer.authorization.user.User;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalTime;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "class_enrollments",
            joinColumns = @JoinColumn(name = "fitness_class_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> enrolledUsers;
}
