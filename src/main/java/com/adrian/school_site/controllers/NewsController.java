package com.adrian.school_site.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adrian.school_site.model.CodeText;
import com.adrian.school_site.model.NewsSiteData;
import com.adrian.school_site.model.Page;
import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.services.SiteDataService;

/**
 * Controller class for the news page of the school web-application.
 */
@Controller
public class NewsController {

	@Autowired
	private SiteDataModel siteModel;

	@Autowired
	private SiteDataService siteService;

	/**
	 * Returns the admin news page {@link ModelAndView} object to be used by the MVC
	 * framework.
	 * 
	 * @return the {@link ModelAndView} object for the news admin page
	 */
	@GetMapping(path = "admin/news")
	public ModelAndView getGeneralPage() {
		final ModelAndView viewModel = new ModelAndView("news");
		siteModel.setCurrentNews(new NewsSiteData());
		siteModel.setNewsSiteData(siteService.getAllNewsData());
		viewModel.addObject("siteModel", siteModel);
		viewModel.addObject("pages", Page.values());
		viewModel.addObject("pic", "/admin/image-news-upload");
		return viewModel;
	}

	@GetMapping(path = "admin/news/select/{title}")
	public String selectNewsData(@PathVariable(name = "title") final String title, Model model) {
		siteModel.setCurrentNews(siteService.getNewsDataByTitle(title));
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pages", Page.values());
		model.addAttribute("pic", "/admin/image-news-upload");
		return "news :: news-page";
	}

	@PostMapping(path = "admin/updateImage")
	public String updateImage(@RequestBody String imageData, Model model) {
		siteModel.getCurrentNews().setImage(imageData);
		model.addAttribute("pic", "/admin/image-news-upload");

		return "news :: #news_insert_image";
	}

	/**
	 * Submits the admin news page {@link ModelAndView} object to be used by the MVC
	 * framework.
	 * 
	 * @return general page redirection text
	 */
	@PostMapping(path = "admin/news")
	public String postGeneralPage(@ModelAttribute SiteDataModel formSiteModel, @RequestParam("file") MultipartFile file,
			Model model, RedirectAttributes redirectAttributes) {
		byte[] imageBytes = file.toString().getBytes();
		formSiteModel.getCurrentNews().setImage(Base64.getEncoder().encodeToString(imageBytes));

		CodeText status = siteService.saveNewsSiteData(formSiteModel);
		model.addAttribute("siteModel", formSiteModel);
		redirectAttributes.addFlashAttribute("message", status.text());

		if (status.code() == 0) {
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} else {
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		}
		return "redirect:news";
	}
}
