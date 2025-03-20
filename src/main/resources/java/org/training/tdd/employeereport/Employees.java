package org.training.tdd.employeereport;

import java.util.Comparator;
import java.util.List;


public record Employees(List<Employee> employeeList) {


    public Employees(List<Employee> employeeList) {
        this.employeeList = employeeList.stream().filter(employee -> employee.age().isAdultAge())
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public List<Employee> get() {
        return employeeList;
    }

}
