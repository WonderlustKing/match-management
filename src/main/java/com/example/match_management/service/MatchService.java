package com.example.match_management.service;

import com.example.match_management.converter.MatchResourceConverter;
import com.example.match_management.dto.MatchOddResource;
import com.example.match_management.dto.MatchResource;
import com.example.match_management.entity.Match;
import com.example.match_management.entity.MatchOdds;
import com.example.match_management.entity.Sport;
import com.example.match_management.exception.ResourceNotFoundException;
import com.example.match_management.repository.MatchOddsRepository;
import com.example.match_management.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchOddsRepository matchOddsRepository;

    private final MatchResourceConverter matchResourceConverter;

    @Autowired
    public MatchService(MatchRepository matchRepository,
	MatchOddsRepository matchOddsRepository, MatchResourceConverter matchResourceConverter) {
	this.matchRepository = matchRepository;
	this.matchOddsRepository = matchOddsRepository;
	this.matchResourceConverter = matchResourceConverter;
    }

    public MatchResource findById(long id) {
	Match match = matchRepository.findById(id).orElse(null);
	if (match == null) {
	    throw new ResourceNotFoundException("Match not found");
	}
	MatchOdds matchOdds = matchOddsRepository.findByMatchId(match.getId()).orElse(null);
	return matchResourceConverter.apply(match, matchOdds);
    }

    public void createMatch(MatchResource matchResource) {
	Match match = new Match();
	MatchOdds matchOdds = new MatchOdds();
	fillMatchFields(match, matchResource);
	fillMatchOddFields(match, matchOdds, matchResource.getMatchOdd());

	matchRepository.save(match);
	matchOddsRepository.save(matchOdds);
    }

    public void updateMatch(MatchResource matchResource) {
	Match match = matchRepository.findById(matchResource.getId())
	    .orElseThrow(() -> new ResourceNotFoundException("Cannot find match to update"));

	MatchOdds matchOdds;
	if (matchResource.getMatchOdd() != null) {
	    matchOdds = matchOddsRepository.findByIdAndMatchId(matchResource.getMatchOdd().getId(), matchResource.getId())
		.orElseThrow(() -> new ResourceNotFoundException("Cannot find matchOdd to update"));
	} else {
	    matchOdds = new MatchOdds();
	}

	fillMatchFields(match, matchResource);
	fillMatchOddFields(match, matchOdds, matchResource.getMatchOdd());
    }

    public void deleteMatch(long id) {
	Match match = matchRepository.findById(id)
	    .orElseThrow(() -> new ResourceNotFoundException("Cannot find match to delete"));

	matchRepository.delete(match);
    }

    public List<MatchResource> findMatchesBy(LocalDate localDate, String team, String sportValue) {
	Sport sport = Sport.valueOf(sportValue);
	return matchRepository.findByMatchDateOrTeamAOrTeamBAndSport(localDate, team, team, sport.getCode())
	    .stream()
	    .map(match -> matchResourceConverter.apply(match, matchOddsRepository.findByMatchId(match.getId()).orElse(null)))
	    .collect(Collectors.toList());
    }

    private void fillMatchFields(Match match, MatchResource matchResource) {
	match.setDescription(matchResource.getDescription());
	match.setMatchDate(matchResource.getMatchDate());
	match.setMatchTime(matchResource.getMatchTime());
	match.setTeamA(matchResource.getTeamA());
	match.setTeamB(matchResource.getTeamB());
	match.setSport(Sport.valueOf(matchResource.getSport()));
    }

    private void fillMatchOddFields(Match match, MatchOdds matchOdds, MatchOddResource matchOddResource) {
	matchOdds.setMatch(match);
	matchOdds.setOdd(matchOddResource.getOdd());
	matchOdds.setSpecifier(matchOddResource.getSpecifier());
    }

}
