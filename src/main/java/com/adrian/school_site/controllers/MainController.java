package com.adrian.school_site.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.services.SiteDataService;

@Controller
public class MainController {
	@Autowired
	private SiteDataModel siteModel;

	@Autowired
	private SiteDataService siteService;

	private static final List<String> PAGES_URLS;

	static {
		PAGES_URLS = new ArrayList<>();
		PAGES_URLS.add("/home");
		PAGES_URLS.add("/resurse");
		PAGES_URLS.add("/personal");
		PAGES_URLS.add("/contact");
	}

	/**
	 * Returns the school home page {@link ModelAndView} object to be used by the
	 * MVC framework.
	 * 
	 * @return the {@link ModelAndView} object for the home page
	 */
	@GetMapping(path = "home")
	public ModelAndView getMainPage() {
		final ModelAndView viewModel = new ModelAndView("home");

		if (siteModel.getGeneralSiteModel() == null) {
			siteModel.setGeneralSiteModel(siteService.getGeneralSiteData());
		}

		if (CollectionUtils.isEmpty(siteModel.getPageSiteData())) {
			siteModel.setPageSiteData(siteService.getAllPageData());
		}

		if (CollectionUtils.isEmpty(siteModel.getImagesSiteData())) {
			siteModel.setImagesSiteData(siteService.getAllImagesData());
		}

		if (CollectionUtils.isEmpty(siteModel.getNewsSiteData())) {
			siteModel.setNewsSiteData(siteService.getAllNewsData());
		}

		viewModel.addObject("siteModel", siteModel);
		viewModel.addObject("pagesUrls", PAGES_URLS);
		return viewModel;
	}

	/**
	 * Returns the school resources page {@link ModelAndView} object to be used by
	 * the MVC framework.
	 * 
	 * @return the {@link ModelAndView} object for the resources page
	 */
	@GetMapping(path = "resurse")
	public ModelAndView getResourcePage() {
		final ModelAndView viewModel = new ModelAndView("resurse");

		if (siteModel.getGeneralSiteModel() == null) {
			siteModel.setGeneralSiteModel(siteService.getGeneralSiteData());
		}

		if (CollectionUtils.isEmpty(siteModel.getResourcesSiteData())) {
			siteModel.setResourcesSiteData(siteService.getAllResourcesData());
		}

		if (CollectionUtils.isEmpty(siteModel.getPageSiteData())) {
			siteModel.setPageSiteData(siteService.getAllPageData());
		}

		viewModel.addObject("siteModel", siteModel);
		viewModel.addObject("pagesUrls", PAGES_URLS);
		return viewModel;
	}

	/**
	 * Returns the school personal page {@link ModelAndView} object to be used by
	 * the MVC framework.
	 * 
	 * @return the {@link ModelAndView} object for the personal page
	 */
	@GetMapping(path = "personal")
	public ModelAndView getPersonalPage() {
		final ModelAndView viewModel = new ModelAndView("personal");

		if (siteModel.getGeneralSiteModel() == null) {
			siteModel.setGeneralSiteModel(siteService.getGeneralSiteData());
		}

		if (CollectionUtils.isEmpty(siteModel.getPageSiteData())) {
			siteModel.setPageSiteData(siteService.getAllPageData());
		}
		
		if (CollectionUtils.isEmpty(siteModel.getStaffSiteData())) {
			siteModel.setStaffSiteData(siteService.getAllStaffData());
		}

		viewModel.addObject("siteModel", siteModel);
		viewModel.addObject("pagesUrls", PAGES_URLS);
		return viewModel;
	}

	/**
	 * Returns the school contact page {@link ModelAndView} object to be used by the
	 * MVC framework.
	 * 
	 * @return the {@link ModelAndView} object for the contact page
	 */
	@GetMapping(path = "contact")
	public ModelAndView getContactPage() {
		final ModelAndView viewModel = new ModelAndView("contact");

		if (siteModel.getGeneralSiteModel() == null) {
			siteModel.setGeneralSiteModel(siteService.getGeneralSiteData());
		}
		
		if (siteModel.getGeneralSiteModel() == null) {
			siteModel.setGeneralSiteModel(siteService.getGeneralSiteData());
		}

		if (CollectionUtils.isEmpty(siteModel.getPageSiteData())) {
			siteModel.setPageSiteData(siteService.getAllPageData());
		}

		viewModel.addObject("siteModel", siteModel);
		viewModel.addObject("pagesUrls", PAGES_URLS);
		return viewModel;
	}
}
