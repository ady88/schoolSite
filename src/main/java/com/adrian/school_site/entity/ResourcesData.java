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
@Table(name = "docs_data", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class ResourcesData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	@Column(name = "resourcedate")
	private Date resourceDate;
	private byte[] doc;
	@Column(name = "orderdoc")
	private int order;

	@Column(name = "doc_content_type", nullable = false)
	private String docContentType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public byte[] getDoc() {
		return doc;
	}

	public void setDoc(byte[] doc) {
		this.doc = doc;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Date getResourceDate() {
		return resourceDate;
	}

	public void setResourceDate(Date resourceDate) {
		this.resourceDate = resourceDate;
	}

	public String getDocContentType() {
		return docContentType;
	}

	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}

}
