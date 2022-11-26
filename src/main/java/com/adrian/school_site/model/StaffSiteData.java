package com.adrian.school_site.model;

public class StaffSiteData {
	private Page info;
	private String firstname;
	private String lastname;
	private String jobtype;
	private String unitname;
	private Integer order;

	public Page getInfo() {
		return info;
	}

	public void setInfo(Page info) {
		this.info = info;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
