package com.adrian.school_site.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adrian.school_site.model.CodeText;
import com.adrian.school_site.model.Page;
import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.model.StaffSiteData;
import com.adrian.school_site.services.SiteDataService;

/**
 * Controller class for the staff page of the school web-application.
 */
@Controller
public class StaffController {

	@Autowired
	private SiteDataModel siteModel;

	@Autowired
	private SiteDataService siteService;

	/**
	 * Returns the admin staff page {@link ModelAndView} object to be used by the
	 * MVC framework.
	 * 
	 * @return the {@link ModelAndView} object for the resources staff page
	 */
	@GetMapping(path = "admin/staff")
	public ModelAndView getResourcePage() {
		final ModelAndView viewModel = new ModelAndView("staff");
		siteModel.setCurrentStaff(new StaffSiteData());
		siteModel.setStaffSiteData(siteService.getAllStaffData());
		viewModel.addObject("siteModel", siteModel);
		viewModel.addObject("pages", Page.values());
		viewModel.addObject("deleteMessage", null);
		return viewModel;
	}

	@GetMapping(path = "admin/staff/select/{firstname}/{lastname}")
	public String selectStaffData(@PathVariable(name = "firstname") final String firstname,
			@PathVariable(name = "lastname") final String lastname, Model model) {
		siteModel.setCurrentStaff(siteService.getStaffByName(firstname, lastname));
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pages", Page.values());
		return "staff :: staff-page";
	}

	@GetMapping(path = "admin/staff/remove/{firstname}/{lastname}")
	public String removeStaffData(@PathVariable(name = "firstname") final String firstname,
			@PathVariable(name = "lastname") final String lastname, Model model) {
		CodeText resultMessage = siteService.deleteStaffSiteData(firstname, lastname);
		siteModel.setStaffSiteData(siteService.getAllStaffData());
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pages", Page.values());
		model.addAttribute("deleteMessage", resultMessage.text());
		return "staff :: staff-list";
	}

	/**
	 * Submits the admin resources page {@link ModelAndView} object to be used by
	 * the MVC framework.
	 * 
	 * @return resources page redirection text
	 * @throws IOException
	 */
	@PostMapping(path = "admin/staff")
	public String postGeneralPage(@ModelAttribute SiteDataModel formSiteModel, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		CodeText status = siteService.saveStaffSiteData(formSiteModel);
		model.addAttribute("siteModel", formSiteModel);
		redirectAttributes.addFlashAttribute("message", status.text());

		if (status.code() == 0) {
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} else {
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		}
		return "redirect:staff";
	}
}
