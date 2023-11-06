package com.example.match_management.service;

import com.example.match_management.converter.MatchOddResourceConverter;
import com.example.match_management.dto.MatchOddResource;
import com.example.match_management.entity.Match;
import com.example.match_management.entity.MatchOdd;
import com.example.match_management.exception.ResourceNotFoundException;
import com.example.match_management.repository.MatchOddsRepository;
import com.example.match_management.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchOddService {

    private final MatchOddsRepository matchOddsRepository;
    private final MatchRepository matchRepository;
    private final MatchOddResourceConverter matchOddResourceConverter;

    public MatchOddService(MatchOddsRepository matchOddsRepository, MatchRepository matchRepository, MatchOddResourceConverter matchOddResourceConverter) {
	this.matchOddsRepository = matchOddsRepository;
	this.matchRepository = matchRepository;
	this.matchOddResourceConverter = matchOddResourceConverter;
    }

    public MatchOddResource findById(long id) {
	return matchOddsRepository.findById(id)
			.map(matchOddResourceConverter::convert)
			.orElseThrow(() -> new ResourceNotFoundException("Cannot find Match Odd with id: " + id));
    }

    public List<MatchOddResource> findMatchOddsByMatchId(long matchId) {
	matchRepository.findById(matchId).orElseThrow(() -> new ResourceNotFoundException("Cannot find match with id: " + matchId));
	return matchOddsRepository.findByMatchId(matchId)
			.stream()
			.map(matchOddResourceConverter::convert)
			.collect(Collectors.toList());
    }

    public MatchOddResource createMatchOdd(MatchOddResource matchOddResource) {
	Match match = matchRepository.findById(matchOddResource.getMatchId()).orElseThrow(() -> new ResourceNotFoundException("Cannot find match with id: " + matchOddResource.getMatchId()));
	MatchOdd matchOdd = new MatchOdd();
	fillMatchOddFields(match, matchOdd, matchOddResource);
	matchOddsRepository.save(matchOdd);
	return matchOddResourceConverter.convert(matchOdd);
    }

    public MatchOddResource updateMatchOdd(MatchOddResource matchOddResource) {
	MatchOdd matchOdd = matchOddsRepository.findById(matchOddResource.getId()).orElseThrow(() -> new ResourceNotFoundException("Cannot find Match Odd with id: " + matchOddResource.getId()));
	Match match = matchRepository.findById(matchOddResource.getMatchId()).orElseThrow(() -> new ResourceNotFoundException("Cannot find Match with id: " + matchOddResource.getMatchId() + " to update Match Odd"));

	fillMatchOddFields(match, matchOdd, matchOddResource);
	matchOddsRepository.save(matchOdd);
	return matchOddResourceConverter.convert(matchOdd);
    }

    public void deleteMatchOdd(Long matchOddId) {
	matchOddsRepository.findById(matchOddId).orElseThrow(() -> new ResourceNotFoundException("Cannot find Match Odd with id: " + matchOddId));
	matchOddsRepository.deleteById(matchOddId);
    }

    private void fillMatchOddFields(Match match, MatchOdd matchOdd, MatchOddResource matchOddResource) {
    	matchOdd.setMatch(match);
    	matchOdd.setOdd(matchOddResource.getOdd());
    	matchOdd.setSpecifier(matchOddResource.getSpecifier());
    }
}
