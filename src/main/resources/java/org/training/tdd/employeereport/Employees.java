package org.training.tdd.employeereport;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public record Employees(List<Employee> employeeList) {


    public Employees(List<Employee> employeeList) {
        this.employeeList = employeeList.stream().filter(employee -> employee.age().isAdultAge())
                                        .sorted(Comparator.reverseOrder())
                                        .toList();
    }

    public int size() {
        return employeeList.size();
    }

    public boolean areAdultEmployees() {
        return employeeList.stream().allMatch(employee -> employee.age().isAdultAge());
    }

    public List<Employee> get() {
        return employeeList;
    }

}
