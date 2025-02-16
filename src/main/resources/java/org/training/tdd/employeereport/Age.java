package org.training.tdd.employeereport;

public record Age(int value) {

    public static final int ADULT_AGE = 18;

    public Age {
        if (value <= 0) {
            throw new IllegalArgumentException("Age must not be 0 or negative");
        }
    }

    public boolean isAdultAge() {
        return (value >= ADULT_AGE);
    }


}