package com.adrian.school_site.model;

import com.adrian.school_site.utils.Constants;

public class ImagesSiteData {
	private Page info;
	private String title;
	private String description;

	private String pageTitle;
	private String image = Constants.TEMPLATE_IMAGE_DATA;
	private Integer order;

	public Page getInfo() {
		return info;
	}

	public void setInfo(Page info) {
		this.info = info;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	@Override
	public String toString() {
		return "{\"info\":\"" + info + "\", \"title\":\"" + title + "\", \"description\":\"" + description + "\", \"pageTitle\":\""
				+ pageTitle + "\", \"order\":" + order + "}";
	}
}
