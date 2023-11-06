package com.example.match_management.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchResource {

    private long id;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    private String description;

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    private LocalDate matchDate;

    public LocalDate getMatchDate() {
	return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
	this.matchDate = matchDate;
    }

    private LocalTime matchTime;

    public LocalTime getMatchTime() {
	return matchTime;
    }

    public void setMatchTime(LocalTime matchTime) {
	this.matchTime = matchTime;
    }

    private String teamA;

    public String getTeamA() {
	return teamA;
    }

    public void setTeamA(String teamA) {
	this.teamA = teamA;
    }

    private String teamB;

    public String getTeamB() {
	return teamB;
    }

    public void setTeamB(String teamB) {
	this.teamB = teamB;
    }

    private String sport;

    public String getSport() {
	return sport;
    }

    public void setSport(String sport) {
	this.sport = sport;
    }

    public MatchOddResource matchOdd;

    public MatchOddResource getMatchOdd() {
	return matchOdd;
    }

    public void setMatchOdd(MatchOddResource matchOdd) {
	this.matchOdd = matchOdd;
    }
}
