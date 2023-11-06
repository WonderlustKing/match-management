package com.example.match_management.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String description;

    @Column(name = "match_date", columnDefinition = "DATE", nullable = false)
    private LocalDate matchDate;

    @Column(name = "match_time", columnDefinition = "TIME", nullable = false)
    private LocalTime matchTime;

    @Column(name = "team_a", nullable = false)
    private String teamA;

    @Column(name = "team_b", nullable = false)
    private String teamB;

    @Column(nullable = false)
    private Sport sport;

    @OneToMany(mappedBy = "match")
    private Set<MatchOdd> odds;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


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

    public String getTeamA() { return teamA; }
    public void setTeamA(String teamA) { this.teamA = teamA; }

    public String getTeamB() { return teamB; }
    public void setTeamB(String teamB) { this.teamB = teamB; }

    public Sport getSport() { return sport; }
    public void setSport(Sport sport) { this.sport = sport; }

    public Set<MatchOdd> getOdds() {
        return odds;
    }

    public void setOdds(Set<MatchOdd> odds) {
        this.odds = odds;
    }
}
