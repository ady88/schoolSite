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
import com.adrian.school_site.model.ImagesSiteData;
import com.adrian.school_site.model.Page;
import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.services.SiteDataService;

/**
 * Controller class for the images page of the school web-application.
 */
@Controller
public class ImagesController {

	static Integer COUNTER = 0;

	@Autowired
	private SiteDataModel siteModel;

	@Autowired
	private SiteDataService siteService;

	/**
	 * Returns the admin iamge page {@link ModelAndView} object to be used by the
	 * MVC framework.
	 * 
	 * @return the {@link ModelAndView} object for the image admin page
	 */
	@GetMapping(path = "admin/images")
	public ModelAndView getImagePage() {
		final ModelAndView viewModel = new ModelAndView("images");
		siteModel.setCurrentImages(new ImagesSiteData());
		siteModel.setImagesSiteData(siteService.getAllImagesData());
		viewModel.addObject("siteModel", siteModel);
		viewModel.addObject("pages", Page.values());
		viewModel.addObject("pic", "/admin/image-images-upload/0");
		viewModel.addObject("deleteMessage", null);
		return viewModel;
	}

	@GetMapping(path = "admin/images/select/{title}")
	public String selectImagesData(@PathVariable(name = "title") final String title, Model model) {
		// this needs to be increased in order for the thymeleaf engine to detect that a
		// new URL is available and display a new image
		COUNTER++;
		siteModel.setCurrentImages(siteService.getImagesDataByTitle(title));
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pages", Page.values());
		model.addAttribute("pic", "/admin/image-images-upload/" + COUNTER);
		return "images :: images-page";
	}

	@PostMapping(path = "admin/updateMainImage")
	public String updateImage(@RequestBody String imageData, Model model) throws UnsupportedEncodingException {
		COUNTER++;

		siteModel.getCurrentImages().setImage(imageData);
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pic", "/admin/image-images-upload/" + COUNTER);
		model.addAttribute("pages", Page.values());
		return "images :: #images_insert_image";
	}

	@GetMapping(path = "admin/images/remove/{title}")
	public String removeImagesData(@PathVariable(name = "title") final String title, Model model) {
		CodeText resultMessage = siteService.deleteImagesSiteData(title);
		siteModel.setImagesSiteData(siteService.getAllImagesData());
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pages", Page.values());
		model.addAttribute("deleteMessage", resultMessage.text());
		return "images :: images-list";
	}

	/**
	 * Submits the admin images page {@link ModelAndView} object to be used by the
	 * MVC framework.
	 * 
	 * @return general page redirection text
	 * @throws IOException
	 */
	@PostMapping(path = "admin/images")
	public String postGeneralPage(@ModelAttribute SiteDataModel formSiteModel, @RequestParam("file") MultipartFile file,
			Model model, RedirectAttributes redirectAttributes) throws IOException {
		byte[] imageBytes = file.getBytes();

		String encodeBase64String = Base64.encodeBase64String(imageBytes);
		new String(Base64.decodeBase64(imageBytes), StandardCharsets.UTF_8);
		formSiteModel.getCurrentImages().setImage(encodeBase64String);

		CodeText status = siteService.saveImagesSiteData(formSiteModel);
		model.addAttribute("siteModel", formSiteModel);
		redirectAttributes.addFlashAttribute("message", status.text());

		if (status.code() == 0) {
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} else {
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		}
		return "redirect:images";
	}
}
