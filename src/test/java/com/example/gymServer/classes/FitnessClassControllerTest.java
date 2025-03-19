package com.example.gymServer.classes;

import com.example.gymServer.authorization.config.JwtService;
import com.example.gymServer.authorization.user.User;
import com.example.gymServer.controllers.FitnessClassController;
import com.example.gymServer.models.FitnessClass;
import com.example.gymServer.services.FitnessClassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FitnessClassController.class)
public class FitnessClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FitnessClassService fitnessClassService;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    private FitnessClass fitnessClass;

    @BeforeEach
    public void setup() {
        fitnessClass = new FitnessClass();
        fitnessClass.setId(1L);
        fitnessClass.setPersonalTrainerId(1L);
        fitnessClass.setStartTime(LocalTime.of(10, 0));
        fitnessClass.setEndTime(LocalTime.of(11, 0));
        fitnessClass.setMaxUsers(30);
        fitnessClass.setDayOfWeek("Monday");
        fitnessClass.setClassName("Yoga Class");

        // Mock JWT token generation
        String jwtToken = "mockJwtToken";
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn(jwtToken);

        // Simulate authentication using a mock user
        User mockUser = mock(User.class);
        mockUser.setId(1);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockUser);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testCreateFitnessClass_Valid() throws Exception {
        // Arrange - simulate valid class creation
        FitnessClass newClass = new FitnessClass();
        newClass.setPersonalTrainerId(2L);
        newClass.setStartTime(LocalTime.of(14, 0));
        newClass.setEndTime(LocalTime.of(15, 0));
        newClass.setMaxUsers(25);
        newClass.setDayOfWeek("Tuesday");
        newClass.setClassName("Pilates Class");

        //when(fitnessClassService.createClass(Mockito.any(FitnessClass.class))).thenReturn(newClass);

        // Act & Assert - validate response
        mockMvc.perform(post("/api/v1/classes")
                        .header("Authorization", "Bearer mockJwtToken")  // Include JWT in the header
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newClass)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.className").value("Pilates Class"))
                .andExpect(jsonPath("$.maxUsers").value(25))
                .andExpect(jsonPath("$.startTime").value("14:00"))
                .andExpect(jsonPath("$.endTime").value("15:00"));
    }

    @Test
    public void testEnrollUser_Success() throws Exception {
        // Mocking JWT token in request header
        String jwtToken = "mockJwtToken";

        // Simulate a successful enrollment for the current user
        //when(fitnessClassService.enrollUser(eq(1L), eq(1))).thenReturn(Optional.of(fitnessClass));

        mockMvc.perform(post("/api/v1/classes/1/enroll")
                        .header("Authorization", "Bearer " + jwtToken))  // JWT in header
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.className").value("Yoga Class"));
    }
}
