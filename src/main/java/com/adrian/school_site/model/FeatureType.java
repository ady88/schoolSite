package com.adrian.school_site.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum FeatureType {
	EMPTY("-"), NEWS("news"), IMAGES("images"), DOCS("docs"), CONTACT("contact"), STAFF_TABLE("staff_table");

	private final String id;

	private static final Map<String, FeatureType> featureTypeByName;
	static {
		featureTypeByName = Arrays.stream(values()).collect(Collectors.toMap(e -> e.id, e -> e));
	}

	FeatureType(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public static Optional<FeatureType> fromId(String value) {
		return Optional.ofNullable(featureTypeByName.get(value));
	}

}
