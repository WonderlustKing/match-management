package com.example.match_management.converter;

import com.example.match_management.dto.MatchOddResource;
import com.example.match_management.entity.MatchOdd;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MatchOddResourceConverter implements Converter<MatchOdd, MatchOddResource> {

    @Override
    public MatchOddResource convert(MatchOdd source) {
	MatchOddResource matchOddResource = new MatchOddResource();
	matchOddResource.setId(source.getId());
	matchOddResource.setMatchId(source.getMatch().getId());
	matchOddResource.setSpecifier(source.getSpecifier());
	matchOddResource.setOdd(source.getOdd());
	return matchOddResource;
    }
}
