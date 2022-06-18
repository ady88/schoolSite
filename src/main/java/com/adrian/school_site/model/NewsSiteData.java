package com.adrian.school_site.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class NewsSiteData {
	private Page info;
	private String title;
	private String description;
	@DateTimeFormat(iso = ISO.DATE)
	private Date date = new Date();
	private String image = "data:image/png;base64,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";
	private String linkLabel;
	private String linkUrl;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
}