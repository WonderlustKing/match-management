package com.example.match_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MatchResource {

    private long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate matchDate;

    @JsonFormat(pattern = "HH:mm")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime matchTime;

    private String teamA;
    private String teamB;
    private String sport;

    private String description;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public LocalDate getMatchDate() {
	return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
	this.matchDate = matchDate;
    }

    public LocalTime getMatchTime() {
	return matchTime;
    }

    public void setMatchTime(LocalTime matchTime) {
	this.matchTime = matchTime;
    }

    public String getTeamA() {
	return teamA;
    }

    public void setTeamA(String teamA) {
	this.teamA = teamA;
    }


    public String getTeamB() {
	return teamB;
    }

    public void setTeamB(String teamB) {
	this.teamB = teamB;
    }

    public String getSport() {
	return sport;
    }

    public void setSport(String sport) {
	this.sport = sport;
    }

}
