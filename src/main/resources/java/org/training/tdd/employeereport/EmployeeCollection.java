package org.training.tdd.employeereport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeCollection {

    private final List<Employee> employeeList;

    public EmployeeCollection(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public EmployeeCollection sortByName() {
        return new EmployeeCollection(employeeList.stream().sorted(Comparator.comparing(
                employee -> employee.name().orElse("")))
                .collect(Collectors.toList()));
    }

    public EmployeeCollection getWithAgeGreaterThanOrEquals(Age age) {
        return new EmployeeCollection(employeeList.stream().filter(
                        employee -> employee.age().value() >= age.value())
                .collect(Collectors.toList()));

    }

    public EmployeeCollection reverse() {
        List<Employee> reversedSortedEmployeeList = new ArrayList<>(this.sortByName().get());
        Collections.reverse(reversedSortedEmployeeList);
        return new EmployeeCollection(reversedSortedEmployeeList);
    }

    public List<Employee> get() {
        return employeeList;
    }

    public static EmployeeCollection create(List<Employee> employees) {
        return new EmployeeCollection(employees);
    }

}
