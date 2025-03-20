package com.example.gymServer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingGoalDTO {
    private Long id;

    @NotBlank(message = "Content must not be blank")
    private String content;

    private boolean finished;

    private Long userId;
}
