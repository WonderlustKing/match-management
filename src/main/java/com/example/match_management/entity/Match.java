package com.example.match_management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @Column
    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Column(name = "match_date", columnDefinition = "DATE")
    private LocalDate matchDate;

    public LocalDate getMatchDate() {
	return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
	this.matchDate = matchDate;
    }

    @Column(name = "match_time", columnDefinition = "TIME")
    private LocalTime matchTime;

    public LocalTime getMatchTime() {
	return matchTime;
    }

    public void setMatchTime(LocalTime matchTime) {
	this.matchTime = matchTime;
    }

    @Column(name = "team_a")
    private String teamA;

    public String getTeamA() { return teamA; }
    public void setTeamA(String teamA) { this.teamA = teamA; }

    @Column(name = "team_b")
    private String teamB;
    public String getTeamB() { return teamB; }
    public void setTeamB(String teamB) { this.teamB = teamB; }

    @Column
    private Sport sport;

    public Sport getSport() { return sport; }
    public void setSport(Sport sport) { this.sport = sport; }
}
