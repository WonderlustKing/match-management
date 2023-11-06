package com.example.match_management.converter;

import com.example.match_management.dto.MatchResource;
import com.example.match_management.entity.Match;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MatchResourceConverter implements Converter<Match, MatchResource> {

    @Override
    public MatchResource convert(Match source) {
	MatchResource matchResource = new MatchResource();
	matchResource.setId(source.getId());
	matchResource.setMatchDate(source.getMatchDate());
	matchResource.setMatchTime(source.getMatchTime());
	matchResource.setDescription(source.getDescription());
	matchResource.setTeamA(source.getTeamA());
	matchResource.setTeamB(source.getTeamB());
	matchResource.setSport(source.getSport().name());
	return matchResource;
    }
}
