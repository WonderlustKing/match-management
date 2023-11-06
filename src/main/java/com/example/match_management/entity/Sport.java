package com.example.match_management.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Sport {
    Football (1), Basketball(2);

    private Sport(int code) {
	this.code = code;
    }
    private final int code;
    public int getCode() { return code; }

    public static Optional<Sport> getByName(String name) {
        return Arrays.stream(Sport.values())
                        .filter(value -> value.name().equals(name))
                        .findAny();
    }
}
