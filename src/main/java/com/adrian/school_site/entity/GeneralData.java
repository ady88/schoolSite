package com.adrian.school_site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "general")
public class GeneralData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "site_name")
	private String sitename;

	@Column(name = "home_page_name")
	private String homePageName;

	@Column(name = "resources_page_name")
	private String resourcePageName;

	@Column(name = "staff_page_name")
	private String staffPageName;

	@Column(name = "about_page_name")
	private String aboutPageName;

	@Column(name = "facebook_address")
	private String facebookAddress;

	@Column(name = "theme")
	private String theme;

	@Column(name = "structure_1")
	private String structure1;

	@Column(name = "structure_2")
	private String structure2;

	@Column(name = "structure_3")
	private String structure3;

	@Column(name = "structure_4")
	private String structure4;

	@Column(name = "map_url")
	private String mapUrl;

	@Column(name = "contact_header")
	private String contactHeader;

	@Column(name = "structures_header")
	private String structuresHeader;

	private String email;

	@Column(name = "address")
	private String adress;

	private String motto;
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomePageName() {
		return homePageName;
	}

	public void setHomePageName(String homePageName) {
		this.homePageName = homePageName;
	}

	public String getResourcePageName() {
		return resourcePageName;
	}

	public void setResourcePageName(String resourcePageName) {
		this.resourcePageName = resourcePageName;
	}

	public String getStaffPageName() {
		return staffPageName;
	}

	public void setStaffPageName(String staffPageName) {
		this.staffPageName = staffPageName;
	}

	public String getAboutPageName() {
		return aboutPageName;
	}

	public void setAboutPageName(String aboutPageName) {
		this.aboutPageName = aboutPageName;
	}

	public String getFacebookAddress() {
		return facebookAddress;
	}

	public void setFacebookAddress(String facebookAddress) {
		this.facebookAddress = facebookAddress;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getStructure1() {
		return structure1;
	}

	public void setStructure1(String structure1) {
		this.structure1 = structure1;
	}

	public String getStructure2() {
		return structure2;
	}

	public void setStructure2(String structure2) {
		this.structure2 = structure2;
	}

	public String getStructure3() {
		return structure3;
	}

	public void setStructure3(String structure3) {
		this.structure3 = structure3;
	}

	public String getStructure4() {
		return structure4;
	}

	public void setStructure4(String structure4) {
		this.structure4 = structure4;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public String getContactHeader() {
		return contactHeader;
	}

	public void setContactHeader(String contactHeader) {
		this.contactHeader = contactHeader;
	}

	public String getStructuresHeader() {
		return structuresHeader;
	}

	public void setStructuresHeader(String structuresHeader) {
		this.structuresHeader = structuresHeader;
	}
}
