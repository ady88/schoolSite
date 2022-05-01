package com.adrian.school_site.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adrian.school_site.model.FeatureType;

@Entity
@Table(name = "page")
public class PageData {
	@Id
	private int id;
	private FeatureType feature1;
	private FeatureType feature2;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FeatureType getFeature1() {
		return feature1;
	}

	public void setFeature1(FeatureType feature1) {
		this.feature1 = feature1;
	}

	public FeatureType getFeature2() {
		return feature2;
	}

	public void setFeature2(FeatureType feature2) {
		this.feature2 = feature2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
