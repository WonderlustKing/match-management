package com.example.match_management.repository;

import com.example.match_management.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByMatchDateOrTeamAOrTeamBAndSport(LocalDate date, String teamA, String teamB, int sport);

    Long deleteById(long id);

}
