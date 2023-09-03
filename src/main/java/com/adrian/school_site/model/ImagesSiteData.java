package com.adrian.school_site.model;

import com.adrian.school_site.utils.Constants;

public class ImagesSiteData {
	private String id;
	private String title;
	private String description;

	private String image = Constants.TEMPLATE_IMAGE_DATA;
	private Integer order;
	private String imageContentType;

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

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"title\":\"" + title + "\", \"description\":\"" + description + "\", \"order\":" + order + "}";
	}

}
