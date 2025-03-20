package com.example.gymServer.perosnalTrainer;

import com.example.gymServer.controllers.PersonalTrainerController;
import com.example.gymServer.models.PersonalTrainer;
import com.example.gymServer.services.PersonalTrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
/*
public class PersonalTrainerControllerTest {

    @InjectMocks
    private PersonalTrainerController personalTrainerController;

    @Mock
    private PersonalTrainerService personalTrainerService;

    private PersonalTrainer trainer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        trainer = new PersonalTrainer(1L, "John", "Doe", "123456789", "john.doe@example.com", "".getBytes());
    }

    @Test
    public void testGetAllPersonalTrainers() {
        List<PersonalTrainer> trainers = Arrays.asList(trainer);
        when(personalTrainerService.getAllPersonalTrainers()).thenReturn(trainers);

        List<PersonalTrainer> result = personalTrainerController.getAllPersonalTrainers();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Doe", result.get(0).getSurname());
        assertEquals("123456789", result.get(0).getPhoneNumber());
        assertEquals("john.doe@example.com", result.get(0).getEmail());
        verify(personalTrainerService, times(1)).getAllPersonalTrainers();
    }

    @Test
    public void testGetPersonalTrainerById() {
        when(personalTrainerService.getPersonalTrainerById(anyLong())).thenReturn(trainer);

        PersonalTrainer result = personalTrainerController.getPersonalTrainerById(1L);

        assertEquals("John", result.getName());
        assertEquals("Doe", result.getSurname());
        assertEquals("123456789", result.getPhoneNumber());
        assertEquals("john.doe@example.com", result.getEmail());
        verify(personalTrainerService, times(1)).getPersonalTrainerById(1L);
    }

    @Test
    public void testCreatePersonalTrainer() {
        when(personalTrainerService.savePersonalTrainer(any(PersonalTrainer.class))).thenReturn(trainer);

        PersonalTrainer result = personalTrainerController.createPersonalTrainer(trainer);

        assertEquals("John", result.getName());
        assertEquals("Doe", result.getSurname());
        assertEquals("123456789", result.getPhoneNumber());
        assertEquals("john.doe@example.com", result.getEmail());
        verify(personalTrainerService, times(1)).savePersonalTrainer(any(PersonalTrainer.class));
    }

    @Test
    public void testDeletePersonalTrainer() {
        doNothing().when(personalTrainerService).deletePersonalTrainer(anyLong());

        personalTrainerController.deletePersonalTrainer(1L);

        verify(personalTrainerService, times(1)).deletePersonalTrainer(1L);
    }
}*/