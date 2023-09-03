package com.adrian.school_site.model;

public class PageSiteData {
	private Page info;
	private String name;

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

	public void setInfo(Page info) {
		this.info = info;
	}
}
