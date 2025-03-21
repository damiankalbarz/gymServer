package com.example.gymServer.authorization.auth;


import com.example.gymServer.authorization.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String phoneNumber;
  private String password;
  private Role role;
}