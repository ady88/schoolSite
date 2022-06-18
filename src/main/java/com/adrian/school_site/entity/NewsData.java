package com.adrian.school_site.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "news_feature", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class NewsData {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	@Column(name = "pageid")
	private int pageId;
	private Date date;
	private String linklabel;
	private String linkurl;
	private byte[] image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLinklabel() {
		return linklabel;
	}

	public void setLinklabel(String linklabel) {
		this.linklabel = linklabel;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
