package com.adrian.school_site.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.adrian.school_site.utils.Constants;

public class NewsSiteData {
	private Page info;
	private String title;
	private String description;
	@DateTimeFormat(iso = ISO.DATE)
	private Date date = new Date();
	private String image = Constants.TEMPLATE_IMAGE_DATA;
	private String linkLabel;
	private String linkUrl;
	private String pageTitle;

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

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
}
