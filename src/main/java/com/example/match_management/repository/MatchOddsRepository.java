package com.example.match_management.repository;

import com.example.match_management.entity.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {

    Optional<MatchOdds> findByMatchId(Long id);

    Optional<MatchOdds> findByIdAndMatchId(Long id, Long matchId);
}
