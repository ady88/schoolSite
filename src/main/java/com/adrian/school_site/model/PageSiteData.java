package com.adrian.school_site.model;

public class PageSiteData {
	private Page info;
	private String name;
	private FeatureType feature1;
	private FeatureType feature2;

	public PageSiteData() {
	}

	public PageSiteData(Page info) {
		this.info = info;
	}

	public Page getInfo() {
		return info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setInfo(Page info) {
		this.info = info;
	}
}
