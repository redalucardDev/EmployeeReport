package org.training.tdd.employeereport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeTest {


    @Test
    void shouldGetAnErrorWhenNameIsAbsent() {
        assertThrows(IllegalArgumentException.class, () -> new Employee(new Name(null), new Age(18)));
        assertThrows(IllegalArgumentException.class, () -> new Employee(new Name(""), new Age(18)));
    }

    @Test
    void shouldGetAnErrorWhenAgeIs0OrNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Employee(new Name("Sepp"), new Age(0)));
        assertThrows(IllegalArgumentException.class, () -> new Employee(new Name("Sepp"), new Age(-1)));
    }


    @ParameterizedTest
    @CsvSource(value = {"'sepp,18;Max,17;Nina,15;mike. michel,51'",
            "'sepp,17;Max,17;Nina,17;mike. michel,17'"
    })
    void shouldGetOnlyAdultEmployees(String input) {
        Employees employees = new Employees(createEmployeeListFrom(input));

        assertThat(employees).matches(EmployeeTest::areAdultEmployees);
    }


    @ParameterizedTest
    @CsvSource(value = {"'l''herbier jean,19;mike. michel,51;sepp,18'",
    })
    void shouldGetEmployeesWithNameOrCompoundNameCapitalized(String input) {
        Employees employees = new Employees(createEmployeeListFrom(input));

        assertThat(employees).matches(EmployeeTest::haveCapitalizedNameOrCompoundName);

    }


    @Test
    void shouldGetEmployeesSortedByTheirNameInDescendingOrder() {
        Employees employees = new Employees(createEmployeeList());
        assertThat(employees).matches(EmployeeTest::areSortedByTheirNameInDescendingOrder);

    }


    private List<Employee> createEmployeeListFrom(String csv) {
        if (csv.isEmpty()) {
            return new ArrayList<>();
        }
        String[] employeeStrings = csv.split(";");
        return Arrays.stream(employeeStrings)
                .map(employee -> {
                    String[] parts = employee.split(",");
                    Name name = new Name(parts[0]);
                    Age age = new Age(Integer.parseInt(parts[1]));
                    return new Employee(name, age);
                })
                .toList();
    }

    private List<Employee> createEmployeeList() {
        return List.of(new Employee(new Name("Max"), new Age(19))
                , new Employee(new Name("Nina"), new Age(15))
                , new Employee(new Name("mike. michel"), new Age(51))
                , new Employee(new Name("sepp"), new Age(18)));
    }


    public static boolean areAdultEmployees(Employees employees) {
        return employees.get().stream().allMatch(employee -> employee.age().isAdultAge());
    }

    public static  boolean haveCapitalizedNameOrCompoundName(Employees employees) {
        return employees.get().stream().allMatch(employee -> employee.name().isCapitalized());
    }

    public static boolean areSortedByTheirNameInDescendingOrder(Employees employees) {
        List<Name>  names = employees.get().stream().map(Employee::name).sorted(Comparator.reverseOrder()).toList();
        return employees.get().stream().map(Employee::name).toList().equals(names);

    }


}
