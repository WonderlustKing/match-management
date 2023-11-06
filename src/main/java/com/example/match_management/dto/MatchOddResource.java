package com.example.match_management.dto;

public class MatchOddResource {

    private long id;

    private long matchId;
    private String specifier;
    private double odd;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getSpecifier() { return specifier; }
    public void setSpecifier(String specifier) { this.specifier = specifier; }

    public double getOdd() { return odd; }
    public void setOdd(double odd) { this.odd = odd; }
}
