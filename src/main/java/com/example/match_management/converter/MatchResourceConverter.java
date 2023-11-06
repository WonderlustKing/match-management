package com.example.match_management.converter;

import com.example.match_management.dto.MatchResource;
import com.example.match_management.entity.Match;
import com.example.match_management.entity.MatchOdds;
import org.springframework.core.convert.converter.Converter;

import java.util.function.BiFunction;

public class MatchResourceConverter implements BiFunction<Match, MatchOdds, MatchResource> {

    private final MatchOddResourceConverter matchOddResourceConverter;

    public MatchResourceConverter(MatchOddResourceConverter matchOddResourceConverter) {
	this.matchOddResourceConverter = matchOddResourceConverter;
    }

    @Override
    public MatchResource apply(Match match, MatchOdds matchOdds) {
	MatchResource matchResource = new MatchResource();
	matchResource.setId(match.getId());
	matchResource.setMatchDate(match.getMatchDate());
	matchResource.setMatchTime(match.getMatchTime());
	matchResource.setDescription(match.getDescription());
	matchResource.setTeamA(match.getTeamA());
	matchResource.setTeamB(match.getTeamB());
	matchResource.setSport(match.getSport().name());
	if (matchOdds != null) {
	    matchResource.setMatchOdd(matchOddResourceConverter.convert(matchOdds));
	}
	return matchResource;
    }
}
