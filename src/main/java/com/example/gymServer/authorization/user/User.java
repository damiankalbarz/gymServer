package com.example.gymServer.authorization.user;


import com.example.gymServer.models.TrainingGoal;
import com.example.gymServer.authorization.token.Token;
import com.example.gymServer.models.FitnessClass;
import com.example.gymServer.models.Subscription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Integer id;
  @NotBlank(message = "First name must not be blank")
  @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
  private String firstname;

  @NotBlank(message = "Last name must not be blank")
  @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
  private String lastname;

  @NotBlank(message = "Email must not be blank")
  @Email(message = "Email should be valid")
  private String email;

  @Getter
  @NotBlank(message = "Phone number must not be blank")
  @Size(min = 9, max = 9, message = "Phone number must be 9")
  private String phoneNumber;

  @NotBlank(message = "Password must not be blank")
  @Size(min = 6, message = "Password must be at least 6 characters")
  private String password;

  @JsonIgnore
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Subscription subscription;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TrainingGoal> trainingGoals;

  @Enumerated(EnumType.STRING)
  private Role role;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  @JsonIgnore
  @ManyToMany(mappedBy = "enrolledUsers")
  private List<FitnessClass> enrolledClasses;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}