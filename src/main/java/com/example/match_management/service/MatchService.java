package com.example.match_management.service;

import com.example.match_management.converter.MatchOddResourceConverter;
import com.example.match_management.converter.MatchResourceConverter;
import com.example.match_management.dto.MatchOddResource;
import com.example.match_management.dto.MatchResource;
import com.example.match_management.entity.Match;
import com.example.match_management.entity.MatchOdd;
import com.example.match_management.entity.Sport;
import com.example.match_management.exception.ResourceNotFoundException;
import com.example.match_management.exception.SportNotFoundException;
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

    private final MatchResourceConverter matchResourceConverter;

    @Autowired
    public MatchService(MatchRepository matchRepository, MatchResourceConverter matchResourceConverter) {
	this.matchRepository = matchRepository;
	this.matchResourceConverter = matchResourceConverter;
    }

    public MatchResource findMatchById(long id) {
	Match match = matchRepository.findById(id).orElse(null);
	if (match == null) {
	    throw new ResourceNotFoundException("Match not found");
	}
	return matchResourceConverter.convert(match);
    }

    public MatchResource createMatch(MatchResource matchResource) {
	Match match = new Match();
	fillMatchFields(match, matchResource);

	matchRepository.save(match);
	return matchResourceConverter.convert(match);
    }

    public MatchResource updateMatch(MatchResource matchResource) {
	Match match = matchRepository.findById(matchResource.getId())
	    .orElseThrow(() -> new ResourceNotFoundException("Cannot find match to update"));

	fillMatchFields(match, matchResource);
	matchRepository.save(match);
	return matchResourceConverter.convert(match);
    }

    public void deleteMatch(long id) {
	Match match = matchRepository.findById(id)
	    .orElseThrow(() -> new ResourceNotFoundException("Cannot find match to delete"));

	matchRepository.delete(match);
    }

    public List<MatchResource> findMatchesBy(LocalDate matchDate, String team, String sportValue) {
	if (matchDate == null && team == null) {
	    throw new IllegalArgumentException("At least one of the following parameters must be specified: 'matchDate' OR 'team'");
	}
	Sport sport = Sport.getByName(sportValue)
			.orElseThrow(() -> new SportNotFoundException(sportValue));

	return matchRepository.findByMatchDateOrTeamAOrTeamBAndSport(matchDate, team, team, sport)
	    .stream()
	    .map(matchResourceConverter::convert)
	    .collect(Collectors.toList());
    }


    private void fillMatchFields(Match match, MatchResource matchResource) {
	match.setDescription(matchResource.getDescription());
	match.setMatchDate(matchResource.getMatchDate());
	match.setMatchTime(matchResource.getMatchTime());
	match.setTeamA(matchResource.getTeamA());
	match.setTeamB(matchResource.getTeamB());
	match.setSport(Sport.getByName(matchResource.getSport()).orElse(null));
    }

}
