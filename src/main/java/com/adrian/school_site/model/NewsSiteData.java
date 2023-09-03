package com.adrian.school_site.model;

import com.adrian.school_site.utils.Constants;

public class NewsSiteData {
	private String id;
	private String title;
	private String description;
	private String image = Constants.TEMPLATE_IMAGE_DATA;
	private String linkLabel;
	private String linkUrl;
	private String imageContentType;

	private int order;

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

	public String getLinkLabel() {
		return linkLabel;
	}

	public void setLinkLabel(String linkLabel) {
		this.linkLabel = linkLabel;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
