package org.training.tdd.employeereport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Employees {

    private final List<Employee> employeeList;

    public Employees(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeesAllowedToWorkOnSunday() {
        return employeeList.stream().filter(Employee::isOrIsOlderThan18)
                .collect(Collectors.toList());
    }

    public List<Employee> getList() {
        return employeeList;
    }

    public List<Employee> getEmployeesSortedByTheirNameInAscendingOrder() {
        return employeeList.stream().sorted(Comparator.comparing(Employee::name))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesWithTheirNameCapitalized() {
        return employeeList.stream().map(
                        employee -> new Employee(new Name(employee.name()).capitalize(), new Age(employee.age())))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesSortedByTheirNameInDescendingOrder() {
        List<Employee> reversedEmployeeList = new ArrayList<>(getEmployeesSortedByTheirNameInAscendingOrder());
        Collections.reverse(reversedEmployeeList);
        return reversedEmployeeList;
    }
}
