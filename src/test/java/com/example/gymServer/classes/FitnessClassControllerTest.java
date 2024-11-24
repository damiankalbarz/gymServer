package com.example.gymServer.classes;

import com.example.gymServer.authorization.user.User;
import com.example.gymServer.authorization.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/*
@WebMvcTest(FitnessClassController.class)
@AutoConfigureMockMvc(addFilters = false)
class FitnessClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FitnessClassService fitnessClassService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FitnessClassController fitnessClassController;

    private FitnessClass fitnessClass;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setEmail("testuser@example.com");
        user.setLastname("test");
        user.setFirstname("test");
        user.setPassword("12345678");

        fitnessClass = new FitnessClass();
        fitnessClass.setId(1L);
        fitnessClass.setClassName("Yoga Class");
    }

    @Test
    @WithMockUser(username = "testuser@example.com", authorities = { "ADMIN", "USER" })
    void testEnrollUserSuccess() throws Exception {
        when(fitnessClassService.enrollUser(1L, 1)).thenReturn(Optional.of(fitnessClass));

        mockMvc.perform(post("/api/v1/classes/1/enroll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.className").value("Yoga Class"));
    }

    @Test
    @WithMockUser(username = "testuser@example.com")
    void testEnrollUserClassNotFound() throws Exception {
        when(fitnessClassService.enrollUser(1L, 1)).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/v1/classes/1/enroll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testuser@example.com")
    void testCancelEnrollmentSuccess() throws Exception {
        when(fitnessClassService.cancelEnrollment(1L, 1)).thenReturn(Optional.of(fitnessClass));

        mockMvc.perform(post("/api/v1/classes/1/cancel-enrollment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.className").value("Yoga Class"));
    }

    @Test
    @WithMockUser(username = "testuser@example.com")
    void testCancelEnrollmentClassNotFound() throws Exception {
        when(fitnessClassService.cancelEnrollment(1L, 1)).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/v1/classes/1/cancel-enrollment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testuser@example.com")
    void testGetEnrolledUsersSuccess() throws Exception {
        when(fitnessClassService.getEnrolledUsers(1L)).thenReturn(List.of(user));

        mockMvc.perform(get("/api/v1/classes/1/enrolled-users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("testuser@example.com"));
    }

    @Test
    @WithMockUser(username = "testuser@example.com")
    void testGetEnrolledUsersClassNotFound() throws Exception {
        when(fitnessClassService.getEnrolledUsers(1L)).thenThrow(new RuntimeException("Class not found"));

        mockMvc.perform(get("/api/v1/classes/1/enrolled-users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
*/