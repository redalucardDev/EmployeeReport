package org.training.tdd.employeereport;

import java.util.Optional;

public record Employee (Optional<String> name, Age age) {

    public Employee {
        name.orElseThrow(() -> new IllegalArgumentException("Name must be provided"));
        name = name.map(
                nameOpt -> Character.toUpperCase(nameOpt.charAt(0)) + nameOpt.substring(1));
    }

}
