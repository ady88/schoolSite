package com.adrian.school_site.controllers;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.school_site.model.SiteDataModel;

@RestController
public class ImageRestController {

	@Autowired
	private SiteDataModel siteModel;

	@GetMapping(value = "/admin/image-news-upload", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getImageAsByteArray() throws IOException {

		String[] imageParts = siteModel.getCurrentNews().getImage().split(",");
		String image = imageParts[0];
		if (imageParts.length > 1) {
			image = imageParts[1];
		}
		return Base64.getDecoder().decode(image);
	}

}
