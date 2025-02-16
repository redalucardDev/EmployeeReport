package org.training.tdd.employeereport;

import java.util.Objects;

public class Employee {

    private Age age;
    private Name name;

    public Employee(Name name, Age age) {
        this.name = name;
        this.age = age;
    }

    public int age() {
        return age.value();
    }

    public String name() {
        return name.value();
    }

    public boolean isOrIsOlderThan18() {
        return (age.value() >= 18);
    }

    @Override
    public boolean equals(Object otherEmployee) {
        if (this == otherEmployee) return true;
        if (otherEmployee == null || getClass() != otherEmployee.getClass()) return false;
        Employee employee = (Employee) otherEmployee;
        return Objects.equals(name, employee.name) && Objects.equals(age, employee.age);
    }


}
