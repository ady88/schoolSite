package com.adrian.school_site.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adrian.school_site.model.FeatureType;
import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.services.SiteDataService;

/**
 * Controller class for the admin pages of the school web-application.
 */
@Controller
public class GeneralController {

	@Autowired
	private SiteDataModel siteModel;

	@Autowired
	private SiteDataService siteService;


	/**
	 * Returns the admin main page {@link ModelAndView} object to be used by the MVC
	 * framework.
	 * 
	 * @return the {@link ModelAndView} object for the main admin page
	 */
	@GetMapping(path = "admin/general")
	public ModelAndView getGeneralPage() {
		final ModelAndView viewModel = new ModelAndView("admin");
		siteModel.setGeneralSiteModel(siteService.getGeneralSiteData());
		siteModel.setPageSiteData(siteService.getAllPageData());
		viewModel.addObject("siteModel", siteModel);
		viewModel.addObject("features", FeatureType.values());
		return viewModel;
	}

	/**
	 * Submits the admin main page {@link ModelAndView} object to be used by the MVC
	 * framework.
	 * 
	 * @return general page redirection text
	 */
	@PostMapping(path = "admin/general")
	public String postGeneralPage(@ModelAttribute SiteDataModel formSiteModel, Model model,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("siteModel", formSiteModel);
		redirectAttributes.addFlashAttribute("message", "Datele au fost salvate cu success!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:general";
	}
}
