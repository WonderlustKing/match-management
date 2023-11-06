package com.example.match_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "match_odds")
public class MatchOdd {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Column(nullable = false)
    private String specifier;

    @Column(nullable = false)
    private double odd;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }

    public String getSpecifier() { return specifier; }
    public void setSpecifier(String specifier) { this.specifier = specifier; }

    public double getOdd() { return odd; }
    public void setOdd(double odd) { this.odd = odd; }
}
