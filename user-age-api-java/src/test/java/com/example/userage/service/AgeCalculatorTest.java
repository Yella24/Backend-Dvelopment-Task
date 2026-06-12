package com.example.userage.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgeCalculatorTest {

    private final AgeCalculator calc = new AgeCalculator();

    @Test
    void birthdayAlreadyPassedThisYear() {
        assertEquals(35, calc.calculateAge(LocalDate.of(1990, 5, 10), LocalDate.of(2025, 6, 1)));
    }

    @Test
    void birthdayIsToday() {
        assertEquals(35, calc.calculateAge(LocalDate.of(1990, 5, 10), LocalDate.of(2025, 5, 10)));
    }

    @Test
    void birthdayLaterThisYear() {
        assertEquals(34, calc.calculateAge(LocalDate.of(1990, 12, 31), LocalDate.of(2025, 1, 1)));
    }

    @Test
    void futureDobClampsToZero() {
        assertEquals(0, calc.calculateAge(LocalDate.of(2099, 1, 1), LocalDate.of(2025, 1, 1)));
    }

    @Test
    void nullValuesReturnZero() {
        assertEquals(0, calc.calculateAge(null, LocalDate.now()));
        assertEquals(0, calc.calculateAge(LocalDate.now(), null));
    }
}
