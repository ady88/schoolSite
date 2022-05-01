package com.adrian.school_site.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Page {
	MAIN("main", 1), SECONDARY("second", 2), THIRD("third", 3), FORTH("forth", 4);

	private final String name;
	private final Integer id;

	private Page(String name, Integer id) {
		this.name = name;
		this.id = id;
	}

	private static final Map<Integer, Page> pagesById;
	static {
		pagesById = Arrays.stream(values()).collect(Collectors.toMap(e -> e.id, e -> e));
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public static Optional<Page> fromInt(int value) {
		return Optional.ofNullable(pagesById.get(value));
	}
}
