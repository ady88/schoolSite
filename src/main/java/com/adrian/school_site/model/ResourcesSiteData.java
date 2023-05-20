package com.adrian.school_site.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ResourcesSiteData {
	private Page info;
	private String name;
	private String description;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date resourceDate;
	private String doc;
	private Integer order;

	public Page getInfo() {
		return info;
	}

	public void setInfo(Page info) {
		this.info = info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Date getResourceDate() {
		return resourceDate;
	}

	public void setResourceDate(Date resourceDate) {
		this.resourceDate = resourceDate;
	}

}
