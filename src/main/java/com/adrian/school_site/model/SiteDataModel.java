package com.adrian.school_site.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SiteDataModel implements Serializable {
	private static final long serialVersionUID = 929114650404323009L;
	private GeneralSiteModel generalSiteModel;
	private List<PageSiteData> pageSiteData = new ArrayList<>();
	private List<NewsSiteData> newsSiteData = new ArrayList<>();
	private List<ShortNewsSiteData> shortNewsSiteData = new ArrayList<>();
	private List<ImagesSiteData> imagesSiteData = new ArrayList<>();
	private List<ResourcesSiteData> resourcesSiteData = new ArrayList<>();
	private List<StaffSiteData> staffSiteData = new ArrayList<>();
	private NewsSiteData currentNews = new NewsSiteData();
	private ImagesSiteData currentImages = new ImagesSiteData();
	private ResourcesSiteData currentResources = new ResourcesSiteData();
	private StaffSiteData currentStaff = new StaffSiteData();

	public SiteDataModel() {
	}

	public GeneralSiteModel getGeneralSiteModel() {
		return generalSiteModel;
	}

	public void setGeneralSiteModel(GeneralSiteModel generalSiteModel) {
		this.generalSiteModel = generalSiteModel;
	}

	public List<PageSiteData> getPageSiteData() {
		return pageSiteData;
	}

	public void setPageSiteData(List<PageSiteData> pageSiteData) {
		this.pageSiteData = pageSiteData;
	}

	public List<NewsSiteData> getNewsSiteData() {
		return newsSiteData;
	}

	public void setNewsSiteData(List<NewsSiteData> newsSiteData) {
		this.newsSiteData = newsSiteData;
	}

	public NewsSiteData getCurrentNews() {
		return currentNews;
	}

	public void setCurrentNews(NewsSiteData currentNews) {
		this.currentNews = currentNews;
	}

	public List<ImagesSiteData> getImagesSiteData() {
		return imagesSiteData;
	}

	public void setImagesSiteData(List<ImagesSiteData> imagesSiteData) {
		this.imagesSiteData = imagesSiteData;
	}

	public ImagesSiteData getCurrentImages() {
		return currentImages;
	}

	public void setCurrentImages(ImagesSiteData currentImages) {
		this.currentImages = currentImages;
	}

	public List<ResourcesSiteData> getResourcesSiteData() {
		return resourcesSiteData;
	}

	public void setResourcesSiteData(List<ResourcesSiteData> resourcesSiteData) {
		this.resourcesSiteData = resourcesSiteData;
	}

	public ResourcesSiteData getCurrentResources() {
		return currentResources;
	}

	public void setCurrentResources(ResourcesSiteData currentResources) {
		this.currentResources = currentResources;
	}

	public List<StaffSiteData> getStaffSiteData() {
		return staffSiteData;
	}

	public void setStaffSiteData(List<StaffSiteData> staffSiteData) {
		this.staffSiteData = staffSiteData;
	}

	public StaffSiteData getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(StaffSiteData currentStaff) {
		this.currentStaff = currentStaff;
	}

	public List<ShortNewsSiteData> getShortNewsSiteData() {
		return shortNewsSiteData;
	}

	public void setShortNewsSiteData(List<ShortNewsSiteData> shortNewsSiteData) {
		this.shortNewsSiteData = shortNewsSiteData;
	}

}
