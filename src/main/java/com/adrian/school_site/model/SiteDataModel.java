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

}
