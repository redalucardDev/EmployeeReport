package org.training.tdd.employeereport;

import java.util.Objects;

public record Employee(Name name,Age age) implements Comparable<Employee> {

    @Override
    public boolean equals(Object otherEmployee) {
        if (this == otherEmployee) return true;
        if (otherEmployee == null || getClass() != otherEmployee.getClass()) return false;
        Employee employee = (Employee) otherEmployee;
        return Objects.equals(name, employee.name) && Objects.equals(age, employee.age);
    }


    @Override
    public int compareTo(Employee otherEmployee) {
        return this.name.compareTo(otherEmployee.name);

    }
}