package com.example.gymServer.classes;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
/*
public class FitnessClassTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidFitnessClass() {
        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setPersonalTrainerId(1L);
        fitnessClass.setStartTime(LocalTime.of(9, 0));
        fitnessClass.setEndTime(LocalTime.of(10, 0));
        fitnessClass.setMaxUsers(30);
        fitnessClass.setDayOfWeek("Monday");
        fitnessClass.setClassName("Yoga");

        Set<ConstraintViolation<FitnessClass>> violations = validator.validate(fitnessClass);
        assertThat(violations).isEmpty();  // no violations expected
    }

    @Test
    public void testNullPersonalTrainerId() {
        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setStartTime(LocalTime.of(9, 0));
        fitnessClass.setEndTime(LocalTime.of(10, 0));
        fitnessClass.setMaxUsers(30);
        fitnessClass.setDayOfWeek("Monday");
        fitnessClass.setClassName("Yoga");

        Set<ConstraintViolation<FitnessClass>> violations = validator.validate(fitnessClass);
        assertThat(violations).isNotEmpty();  // should have violations
        assertThat(violations).anyMatch(violation -> violation.getMessage().contains("Personal Trainer ID must not be null"));
    }

    @Test
    public void testInvalidMaxUsers() {
        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setPersonalTrainerId(1L);
        fitnessClass.setStartTime(LocalTime.of(9, 0));
        fitnessClass.setEndTime(LocalTime.of(10, 0));
        fitnessClass.setMaxUsers(101);  // exceeding the maximum limit of 100
        fitnessClass.setDayOfWeek("Monday");
        fitnessClass.setClassName("Yoga");

        Set<ConstraintViolation<FitnessClass>> violations = validator.validate(fitnessClass);
        assertThat(violations).isNotEmpty();  // should have violations
        assertThat(violations).anyMatch(violation -> violation.getMessage().contains("Maximum users cannot exceed 100"));
    }

    @Test
    public void testBlankDayOfWeek() {
        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setPersonalTrainerId(1L);
        fitnessClass.setStartTime(LocalTime.of(9, 0));
        fitnessClass.setEndTime(LocalTime.of(10, 0));
        fitnessClass.setMaxUsers(30);
        fitnessClass.setDayOfWeek("");  // blank day of the week
        fitnessClass.setClassName("Yoga");

        Set<ConstraintViolation<FitnessClass>> violations = validator.validate(fitnessClass);
        assertThat(violations).isNotEmpty();  // should have violations
        assertThat(violations).anyMatch(violation -> violation.getMessage().contains("Day of the week must not be blank"));
    }

    @Test
    public void testBlankClassName() {
        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setPersonalTrainerId(1L);
        fitnessClass.setStartTime(LocalTime.of(9, 0));
        fitnessClass.setEndTime(LocalTime.of(10, 0));
        fitnessClass.setMaxUsers(30);
        fitnessClass.setDayOfWeek("Monday");
        fitnessClass.setClassName("");  // blank class name

        Set<ConstraintViolation<FitnessClass>> violations = validator.validate(fitnessClass);
        assertThat(violations).isNotEmpty();  // should have violations
        assertThat(violations).anyMatch(violation -> violation.getMessage().contains("Class name must not be blank"));
    }
}
*/