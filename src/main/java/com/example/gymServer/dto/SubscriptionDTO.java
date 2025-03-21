package com.example.gymServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO {
    
    private Long id;
    
    private Long userId;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
}
