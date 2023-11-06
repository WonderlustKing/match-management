package com.example.match_management.repository;

import com.example.match_management.entity.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchOddsRepository extends JpaRepository<MatchOdd, Long> {

    List<MatchOdd> findByMatchId(Long id);

}
