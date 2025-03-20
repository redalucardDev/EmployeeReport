package org.training.tdd.employeereport;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public record Name(String value) implements Comparable<Name> {

    public Name {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("Employee name must not be empty");
        }

        value = capitalize(value);
    }


    private String capitalize(String value) {
        //For the moment, we are only using spaces, hyphens, apostrophes, and dots as the valid delimiters for separating name.
        String nameDelimitersRegex = "[\\s-'\\.]";
        return Arrays.stream(value.split("(?=" + nameDelimitersRegex + ")|(?<=" + nameDelimitersRegex + ")"))
                .map(part -> part.matches(nameDelimitersRegex + "+") ? part
                        : Character.toUpperCase(part.charAt(0)) + part.substring(1).toLowerCase())
                .collect(Collectors.joining());
    }

    public boolean isCapitalized() {
        return this.value.matches("([A-Z][a-z]*)([-' .]*[A-Z][a-z]*)*");
    }

    @Override
    public int compareTo(Name otherName) {
        return this.value.compareTo(otherName.value);
    }
}