package com.example.gymServer.authorization.user;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String firstname;
    private String lastname;
    private String phoneNumber;
}
