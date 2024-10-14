package com.example.gymServer.perosnalTrainer;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonalTrainerTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidPersonalTrainer() {
        PersonalTrainer personalTrainer = new PersonalTrainer(
                1L,
                "John",
                "Doe",
                "+123456789",
                "john.doe@example.com",
                new byte[]{}
        );

        Set<ConstraintViolation<PersonalTrainer>> violations = validator.validate(personalTrainer);
        assertThat(violations).isEmpty();  // no violations expected
    }

    @Test
    public void testInvalidPhoneNumber() {
        PersonalTrainer personalTrainer = new PersonalTrainer(
                1L,
                "John",
                "Doe",
                "invalid-phone",
                "john.doe@example.com",
                new byte[]{}
        );

        Set<ConstraintViolation<PersonalTrainer>> violations = validator.validate(personalTrainer);
        assertThat(violations).isNotEmpty();  // should have violations
        assertThat(violations).anyMatch(violation -> violation.getMessage().contains("Phone number must be a valid format"));
    }

    @Test
    public void testBlankName() {
        PersonalTrainer personalTrainer = new PersonalTrainer(
                1L,
                "",
                "Doe",
                "+123456789",
                "john.doe@example.com",
                new byte[]{}
        );

        Set<ConstraintViolation<PersonalTrainer>> violations = validator.validate(personalTrainer);
        assertThat(violations).isNotEmpty();  // should have violations
        assertThat(violations).anyMatch(violation -> violation.getMessage().contains("Name must not be blank"));
    }

    @Test
    public void testInvalidEmail() {
        PersonalTrainer personalTrainer = new PersonalTrainer(
                1L,
                "John",
                "Doe",
                "+123456789",
                "invalid-email",
                new byte[]{}
        );

        Set<ConstraintViolation<PersonalTrainer>> violations = validator.validate(personalTrainer);
        assertThat(violations).isNotEmpty();  // should have violations
        assertThat(violations).anyMatch(violation -> violation.getMessage().contains("Email should be valid"));
    }
}
