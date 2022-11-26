package com.adrian.school_site.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.adrian.school_site.model.Page;
import com.adrian.school_site.model.ResourcesSiteData;
import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.services.SiteDataService;

/**
 * Controller class for the resources page of the school web-application.
 */
@Controller
public class ResourcesController {

	@Autowired
	private SiteDataModel siteModel;

	@Autowired
	private SiteDataService siteService;

	/**
	 * Returns the admin resources page {@link ModelAndView} object to be used by
	 * the MVC framework.
	 * 
	 * @return the {@link ModelAndView} object for the resources admin page
	 */
	@GetMapping(path = "admin/resources")
	public ModelAndView getResourcePage() {
		final ModelAndView viewModel = new ModelAndView("resources");
		siteModel.setCurrentResources(new ResourcesSiteData());
		siteModel.setResourcesSiteData(siteService.getAllResourcesData());
		viewModel.addObject("siteModel", siteModel);
		viewModel.addObject("pages", Page.values());
		viewModel.addObject("deleteMessage", null);
		return viewModel;
	}

	@GetMapping(path = "admin/resources/select/{name}")
	public String selectImagesData(@PathVariable(name = "name") final String name, Model model) {
		siteModel.setCurrentResources(siteService.getResourcesByTitle(name));
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pages", Page.values());
		return "resources :: resources-page";
	}

	@GetMapping(path = "admin/resources/remove/{name}")
	public String removeImagesData(@PathVariable(name = "name") final String name, Model model) {
		CodeText resultMessage = siteService.deleteResourcesSiteData(name);
		siteModel.setResourcesSiteData(siteService.getAllResourcesData());
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pages", Page.values());
		model.addAttribute("deleteMessage", resultMessage.text());
		return "resources :: resources-list";
	}

	@PostMapping(path = "admin/updateResource")
	public String updateImage(@RequestBody String resourceData, Model model) throws UnsupportedEncodingException {

		siteModel.getCurrentResources().setDoc(resourceData);
		model.addAttribute("siteModel", siteModel);
		model.addAttribute("pages", Page.values());
		return "resources :: resources-list";
	}

	/**
	 * Submits the admin resources page {@link ModelAndView} object to be used by
	 * the MVC framework.
	 * 
	 * @return resources page redirection text
	 * @throws IOException
	 */
	@PostMapping(path = "admin/resources")
	public String postGeneralPage(@ModelAttribute SiteDataModel formSiteModel, @RequestParam("file") MultipartFile file,
			Model model, RedirectAttributes redirectAttributes) throws IOException {
		byte[] docBytes = file.getBytes();

		String encodeBase64String = Base64.encodeBase64String(docBytes);
		new String(Base64.decodeBase64(docBytes), StandardCharsets.UTF_8);
		System.out.println("ADRIAN sss");
		System.out.println(encodeBase64String);
		formSiteModel.getCurrentResources().setDoc(encodeBase64String);

		CodeText status = siteService.saveResourcesSiteData(formSiteModel);
		model.addAttribute("siteModel", formSiteModel);
		redirectAttributes.addFlashAttribute("message", status.text());

		if (status.code() == 0) {
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} else {
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		}
		return "redirect:resources";
	}

	@GetMapping(value = "/download/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable(name = "id") String id, HttpServletResponse response) {
		ResourcesSiteData data = siteService.getResourcesByTitle(id);

		// retrieve contents of "C:/tmp/report.pdf" that were written in showHelp
		byte[] contents = Base64.decodeBase64(data.getDoc());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		// Here you have to set the actual filename of your pdf
		String filename = "output.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> fileResponse = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return fileResponse;
	}

}
