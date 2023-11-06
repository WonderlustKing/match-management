package com.example.match_management.dto;

public class MatchOddResource {

    private long id;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    private String specifier;
    public String getSpecifier() { return specifier; }
    public void setSpecifier(String specifier) { this.specifier = specifier; }

    private double odd;
    public double getOdd() { return odd; }
    public void setOdd(double odd) { this.odd = odd; }
}
