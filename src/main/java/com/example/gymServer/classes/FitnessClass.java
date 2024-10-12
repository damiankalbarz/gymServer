package com.example.gymServer.classes;

import com.example.gymServer.authorization.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class FitnessClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Personal Trainer ID must not be null")
    private Long personalTrainerId;

    @NotNull(message = "Start time must not be null")
    private LocalTime startTime;

    @NotNull(message = "End time must not be null")
    private LocalTime endTime;

    @Min(value = 1, message = "Maximum users must be at least 1")
    @Max(value = 100, message = "Maximum users cannot exceed 100")
    private int maxUsers;

    @NotBlank(message = "Day of the week must not be blank")
    private String dayOfWeek;

    @NotBlank(message = "Class name must not be blank")
    private String className;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "class_enrollments",
            joinColumns = @JoinColumn(name = "fitness_class_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> enrolledUsers;
}
