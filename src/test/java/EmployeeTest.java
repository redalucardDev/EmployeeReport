import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.training.tdd.employeereport.Age;
import org.training.tdd.employeereport.Employee;
import org.training.tdd.employeereport.EmployeeCollection;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class EmployeeTest {


    @Test
    void shouldGetAListOf5Employees() {
        List<Employee> createdEmployeeList = createEmployeeList();
        EmployeeCollection employees = EmployeeCollection.create(
                createEmployeeList());

        assertThat(employees.get()
        ).hasSize(5).containsExactlyInAnyOrder(
                new Employee(createdEmployeeList.get(0).name(), createdEmployeeList.get(0).age()),
                new Employee(createdEmployeeList.get(1).name(), createdEmployeeList.get(1).age()),
                new Employee(createdEmployeeList.get(2).name(), createdEmployeeList.get(2).age()),
                new Employee(createdEmployeeList.get(3).name(), createdEmployeeList.get(3).age()),
                new Employee(createdEmployeeList.get(4).name(), createdEmployeeList.get(4).age()));

    }

    @Test
    void shouldGetOnlyEmployeesWithAgeGreaterThan18() {
        EmployeeCollection employees = EmployeeCollection.create(
                createEmployeeList());

        assertThat(employees.getWithAgeGreaterThanOrEquals(new Age(18)).get()
        ).hasSize(3).allSatisfy(employee -> Assertions.assertThat(
                employee.age().value()).isGreaterThanOrEqualTo(18));
    }

    @Test
    void shouldThrowExceptionWhenNameIsMissing() {
        assertThrows(IllegalArgumentException.class, () -> new Employee(Optional.empty(), new Age(18)));
    }


    @Test
    void shouldEmployeesWithSortedNames() {
        EmployeeCollection employees = EmployeeCollection.create(
                createEmployeeList()).sortByName();

        Assertions.assertThat(employees.get())
                .hasSize(5)
                .extracting(employee -> employee.name().orElse(""))
                .isSortedAccordingTo((name1, name2) -> name1.compareTo(name2));
    }


    @Test
    void shouldEmployeesWithSortedNamesInDescendingOrder() {
        EmployeeCollection employees = EmployeeCollection.create(
                createEmployeeList()).sortByName().reverse();


        Assertions.assertThat(employees.get())
                .hasSize(5)
                .extracting(employee -> employee.name().orElse(""))
                .isSortedAccordingTo((name1, name2) -> name2.compareTo(name1));

    }


    @Test
    void shouldEmployeesWithCapitalizedNames() {
        EmployeeCollection employees = EmployeeCollection.create(
                createEmployeeList());


        Assertions.assertThat(employees.get())
                .allSatisfy(employee ->
                        Assertions.assertThat(employee.name().orElse("").substring(0, 1))
                                .matches("[A-Z]")
                );
    }


    private static List<Employee> createEmployeeList() {
        return List.of(new Employee(Optional.of("max"), new Age(17)),
                new Employee(Optional.of("sepp"), new Age(18)),
                new Employee(Optional.of("nina"), new Age(20)),
                new Employee(Optional.of("mike"), new Age(51)),
                new Employee(Optional.of("steve"), new Age(16)));
    }


}
