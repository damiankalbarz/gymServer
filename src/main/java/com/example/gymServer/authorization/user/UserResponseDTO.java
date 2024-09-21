package com.example.gymServer.authorization.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
}
