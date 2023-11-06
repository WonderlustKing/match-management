package com.example.match_management.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SportEntityConverter implements AttributeConverter<Sport, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Sport sport) {
	if (sport == null) {
	    return null;
	}
	return sport.getCode();
    }

    @Override
    public Sport convertToEntityAttribute(Integer code) {
	return null;
    }
}
