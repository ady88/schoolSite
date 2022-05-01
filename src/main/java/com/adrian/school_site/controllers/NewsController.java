package com.adrian.school_site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for the news page of the school web-application.
 */
@Controller
public class NewsController {
	/**
	 * Returns the admin news page {@link ModelAndView} object to be used by the MVC
	 * framework.
	 * 
	 * @return the {@link ModelAndView} object for the news admin page
	 */
	@GetMapping(path = "admin/news")
	public ModelAndView getGeneralPage() {
		final ModelAndView viewModel = new ModelAndView("news");
		return viewModel;
	}
}
