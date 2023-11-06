package com.example.match_management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;

@Entity
@Table
public class MatchOdds {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }

    private String specifier;
    public String getSpecifier() { return specifier; }
    public void setSpecifier(String specifier) { this.specifier = specifier; }

    private double odd;
    public double getOdd() { return odd; }
    public void setOdd(double odd) { this.odd = odd; }
}
