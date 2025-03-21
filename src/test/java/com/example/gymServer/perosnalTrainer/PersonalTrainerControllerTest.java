package com.example.gymServer.personalTrainer;

import com.example.gymServer.controllers.PersonalTrainerController;
import com.example.gymServer.dto.PersonalTrainerDTO;
import com.example.gymServer.models.PersonalTrainer;
import com.example.gymServer.services.PersonalTrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PersonalTrainerControllerTest {

    @InjectMocks
    private PersonalTrainerController personalTrainerController;

    @Mock
    private PersonalTrainerService personalTrainerService;

    private PersonalTrainerDTO trainer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        trainer = new PersonalTrainerDTO(1L, "John", "Doe", "123456789", "john.doe@example.com", "".getBytes());
    }

    @Test
    public void testGetAllPersonalTrainers() {
        List<PersonalTrainerDTO> trainers = Arrays.asList(trainer);
        when(personalTrainerService.getAllPersonalTrainers()).thenReturn(trainers);

        ResponseEntity<List<PersonalTrainerDTO>> response = personalTrainerController.getAllPersonalTrainers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getName());
        verify(personalTrainerService, times(1)).getAllPersonalTrainers();
    }

    @Test
    public void testGetPersonalTrainerById() {
        when(personalTrainerService.getPersonalTrainerById(anyLong())).thenReturn(trainer);

        ResponseEntity<PersonalTrainerDTO> response = personalTrainerController.getPersonalTrainerById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().getName());
        verify(personalTrainerService, times(1)).getPersonalTrainerById(1L);
    }

    @Test
    public void testGetPersonalTrainerById_NotFound() {
        when(personalTrainerService.getPersonalTrainerById(anyLong())).thenReturn(null);

        ResponseEntity<PersonalTrainerDTO> response = personalTrainerController.getPersonalTrainerById(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(personalTrainerService, times(1)).getPersonalTrainerById(1L);
    }

    @Test
    public void testCreatePersonalTrainer() {
        when(personalTrainerService.savePersonalTrainer(any(PersonalTrainerDTO.class))).thenReturn(trainer);

        ResponseEntity<PersonalTrainerDTO> response = personalTrainerController.createPersonalTrainer(trainer);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().getName());
        verify(personalTrainerService, times(1)).savePersonalTrainer(any(PersonalTrainerDTO.class));
    }

    @Test
    public void testDeletePersonalTrainer() {
        doNothing().when(personalTrainerService).deletePersonalTrainer(anyLong());

        ResponseEntity<Void> response = personalTrainerController.deletePersonalTrainer(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(personalTrainerService, times(1)).deletePersonalTrainer(1L);
    }
}
