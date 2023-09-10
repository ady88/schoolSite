package com.adrian.school_site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "other")
public class OtherData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "use_top_image")
	private Boolean useTopImage;
	@Column(name = "use_main_image")
	private Boolean useMainImage;
	@Column(name = "top_image")
	private byte[] topImage;
	@Column(name = "main_image")
	private byte[] mainImage;

	@Column(name = "top_image_content_type")
	private String topImageContentType;

	@Column(name = "main_image_content_type")
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

	public byte[] getTopImage() {
		return topImage;
	}

	public void setTopImage(byte[] topImage) {
		this.topImage = topImage;
	}

	public byte[] getMainImage() {
		return mainImage;
	}

	public void setMainImage(byte[] mainImage) {
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
