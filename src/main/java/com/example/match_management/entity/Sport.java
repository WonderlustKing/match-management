package com.example.match_management.entity;

public enum Sport {
    Football (1), Basketball(2);

    private Sport(int code) {
	this.code = code;
    }
    private final int code;
    public int getCode() { return code; }
}
