package org.training.tdd.employeereport;

public record Age(int value) {

    public Age {
        if (value <= 0) {
            throw new IllegalArgumentException("Age must not be 0 or negative");
        }
    }

}
