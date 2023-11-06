package com.example.match_management.exception;

public class SportNotFoundException extends RuntimeException {

    public SportNotFoundException(String sport) {
	super("Cannot find sport: " + sport);
    }
}
