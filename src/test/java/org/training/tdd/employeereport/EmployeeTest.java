package org.training.tdd.employeereport;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeTest {


    @Test
    void shouldKnowAllEmployees() {
        Employees employees = new Employees(createEmployeeList());
        List<Employee> createdEmployeeList = createEmployeeList();

        List<Employee> employeesList = employees.getList();

        assertThat(employeesList).hasSize(4).containsExactlyInAnyOrder(createdEmployeeList.toArray(Employee[]::new));
    }

    @Test
    void shouldBeAndErrorWhenNameIsEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> new Employee(new Name(null), new Age(18)));
        assertThrows(IllegalArgumentException.class, () -> new Employee(new Name(""), new Age(18)));
    }

    @Test
    void shouldGetAnErrorWhenIs0OrNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Employee(new Name("Sepp"), new Age(0)));
        assertThrows(IllegalArgumentException.class, () -> new Employee(new Name("Sepp"), new Age(-1)));
    }

    @Test
    void shouldKnowEmployeesAllowedToWorkOnSunday() {
        Employees employees = new Employees(createEmployeeList());

        List<Employee> employeesAllowedToWorkOnSunday = employees.getEmployeesAllowedToWorkOnSunday();


        assertThat(employeesAllowedToWorkOnSunday)
                .hasSize(2)
                .allSatisfy(employee -> Assertions.assertThat(employee.age()).isGreaterThanOrEqualTo(18));

    }

    @Test
    void shouldGetEmployeesSortedByTheirName() {
        Employees employees = new Employees(createEmployeeList());

        List<Employee> employeesAllowedToWorkOnSundayList = employees.getEmployeesSortedByTheirNameInAscendingOrder();

        assertThat(employeesAllowedToWorkOnSundayList)
                .hasSize(4)
                .extracting(Employee::name)
                .isSortedAccordingTo(Comparator.naturalOrder());

    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "anne, Anne",
                    "anne sophie, Anne Sophie",
                    "anne sophie margorie, Anne Sophie Margorie",
                    "jean-baptiste, Jean-Baptiste",
                    "mar anne-francoise, Mar Anne-Francoise",
                    "l'herbier jean, L'Herbier Jean",
                    "Jean. Michel Maxim,  Jean. Michel Maxim"
            }
    )
    void shouldGetACapitalizedName(String input, String expected) {
        Name name = new Name(input);

        Name capitalizedName = name.capitalize();

        assertThat(capitalizedName.value()).isEqualTo(expected);


    }

    @Test
    void shouldGetEmployeesWithTheirNameCapitalized() {
        List<Employee> employees = new Employees(createEmployeeList()).getEmployeesWithTheirNameCapitalized();

        Assertions.assertThat(employees).contains(new Employee(new Name("Mike. Michel"), new Age(51)));

    }

    @Test
    void shouldGetEmployeesSortedByTheirNameInDescendingOrder() {
        Employees employees = new Employees(createEmployeeList());

        List<Employee> employeesAllowedToWorkOnSundayList = employees.getEmployeesSortedByTheirNameInDescendingOrder();

        assertThat(employeesAllowedToWorkOnSundayList)
                .hasSize(4)
                .extracting(Employee::name)
                .isSortedAccordingTo(Comparator.reverseOrder());

    }

    private List<Employee> createEmployeeList() {
        return List.of(
                new Employee(new Name("sepp"), new Age(18)),
                new Employee(new Name("Max"), new Age(17)),
                new Employee(new Name("Nina"), new Age(15)),
                new Employee(new Name("mike. michel"), new Age(51))
        );
    }

}
