package com.adrian.school_site.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FeatureTypeConverter implements AttributeConverter<FeatureType, String> {

	@Override
	public String convertToDatabaseColumn(FeatureType attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute.getId();
	}

	@Override
	public FeatureType convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}

		return FeatureType.fromId(dbData).orElse(null);
	}
}
