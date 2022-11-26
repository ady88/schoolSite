package com.adrian.school_site.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
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

	static Integer COUNTER = 0;
	
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
	public ModelAndView getNewsPage() {
		final ModelAndView viewModel = new ModelAndView("news");
		siteModel.setCurrentNews(new NewsSiteData());
		siteModel.setNewsSiteData(siteService.getAllNewsData());
		viewModel.addObject("siteModel", siteModel);
		viewModel.addObject("pages", Page.values());
		viewModel.addObject("pic", "/admin/image-news-upload/0");
		viewModel.addObject("deleteMessage", null);
		return viewModel;
	}

	@GetMapping(path = "admin/news/select/{title}")
	public String selectNewsData(@PathVariable(name = "title") final String title, Model model) {
		// this needs to be increased in order for the thymeleaf engine to detect that a
		// new URL is available and display a new image
		COUNTER++;
		siteModel.setCurrentNews(siteService.getNewsDataByTitle(title));
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pages", Page.values());
		model.addAttribute("pic", "/admin/image-news-upload/" + COUNTER);
		return "news :: news-page";
	}
	
	@GetMapping(path = "admin/news/remove/{title}")
	public String removeNewsData(@PathVariable(name = "title") final String title, Model model) {
		CodeText resultMessage = siteService.deleteNewsSiteData(title);
		siteModel.setNewsSiteData(siteService.getAllNewsData());
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pages", Page.values());
		model.addAttribute("deleteMessage", resultMessage.text());
//		model.addAttribute("pic", "/admin/image-news-upload/" + COUNTER);
//		COUNTER++;
		return "news :: news-list";
	}

	@PostMapping(path = "admin/updateImage")
	public String updateImage(@RequestBody String imageData, Model model) throws UnsupportedEncodingException {
		System.out.println("ADRIAN 14");
//		System.out.println(imageData);
//		byte[] data = Base64.getDecoder().decode(imageData);
//		imageData = new String(data, StandardCharsets.UTF_8);
		
		siteModel.getCurrentNews().setImage(imageData);
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pic", "/admin/image-news-upload/" + COUNTER);
		model.addAttribute("pages", Page.values());
		COUNTER++;
		System.out.println(COUNTER);
		return "news :: #news_insert_image";
	}

	/**
	 * Submits the admin news page {@link ModelAndView} object to be used by the MVC
	 * framework.
	 * 
	 * @return general page redirection text
	 * @throws IOException 
	 */
	@PostMapping(path = "admin/news")
	public String postGeneralPage(@ModelAttribute SiteDataModel formSiteModel, @RequestParam("file") MultipartFile file,
			Model model, RedirectAttributes redirectAttributes) throws IOException {
		byte[] imageBytes = file.getBytes();

		
		
		String encodeBase64String = Base64.encodeBase64String(imageBytes);
		new String(Base64.decodeBase64(imageBytes), StandardCharsets.UTF_8);
		formSiteModel.getCurrentNews().setImage(encodeBase64String);

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
