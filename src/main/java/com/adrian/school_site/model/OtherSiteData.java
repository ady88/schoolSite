package com.adrian.school_site.model;

import com.adrian.school_site.utils.Constants;

public class OtherSiteData {
	private int id;
	private Boolean useTopImage;
	private Boolean useMainImage;
	private String topImage = Constants.TEMPLATE_IMAGE_DATA;;
	private String mainImage = Constants.TEMPLATE_IMAGE_DATA;;

	private String topImageContentType;

	private String mainImageContentType;

	private String theme;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getUseTopImage() {
		return useTopImage;
	}

	public void setUseTopImage(Boolean useTopImage) {
		this.useTopImage = useTopImage;
	}

	public Boolean getUseMainImage() {
		return useMainImage;
	}

	public void setUseMainImage(Boolean useMainImage) {
		this.useMainImage = useMainImage;
	}

	public String getTopImage() {
		return topImage;
	}

	public void setTopImage(String topImage) {
		this.topImage = topImage;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public String getTopImageContentType() {
		return topImageContentType;
	}

	public void setTopImageContentType(String topImageContentType) {
		this.topImageContentType = topImageContentType;
	}

	public String getMainImageContentType() {
		return mainImageContentType;
	}

	public void setMainImageContentType(String mainImageContentType) {
		this.mainImageContentType = mainImageContentType;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}
