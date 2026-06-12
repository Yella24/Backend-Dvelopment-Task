package com.example.userage.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

/**
 * Pure age-calculation utility.
 *
 * <p>Why a dedicated component? It's the one piece of business logic worth
 * unit-testing in isolation, and keeping it as a Spring bean lets us inject
 * a clock-aware variant in tests if needed.</p>
 */
@Component
public class AgeCalculator {

    /**
     * Returns the full years between {@code dob} and {@code today}. Future or
     * null DOBs return 0 to keep the API shape stable.
     */
    public int calculateAge(LocalDate dob, LocalDate today) {
        if (dob == null || today == null) {
            return 0;
        }
        if (dob.isAfter(today)) {
            return 0;
        }
        return Period.between(dob, today).getYears();
    }
}
