package com.adrian.school_site.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.adrian.school_site.model.ImagesSiteData;
import com.adrian.school_site.model.NewsSiteData;
import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.utils.Constants;

@RestController
public class ImageUploadRestController {

	@Autowired
	private SiteDataModel siteModel;

	@GetMapping(value = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getImageAsByteArrayForHome(@PathVariable(name = "fileName") final String fileName)
			throws IOException {
		List<ImagesSiteData> images = siteModel.getImagesSiteData();
		Optional<ImagesSiteData> wrappedImage = images.stream().filter(e -> e.getId().equals(fileName)).findFirst();
		String currentImage = wrappedImage.isPresent() ? wrappedImage.get().getImage() : Constants.TEMPLATE_IMAGE_DATA;

		if (wrappedImage.isEmpty() || StringUtils.isEmpty(wrappedImage.get().getImage())) {
			currentImage = Constants.TEMPLATE_IMAGE_DATA;
		}

		String[] imageParts = currentImage.split(",");
		String image = imageParts[0];
		if (imageParts.length > 1) {
			image = imageParts[1];

		} else {
			imageParts = currentImage.split("%2C");
			if (imageParts.length > 1) {
				image = imageParts[1];
			}
		}

		String decode = UriUtils.decode(image, "UTF-8");

//		System.out.println("ADRIAN 2222");
//
//		System.out.println(image);
//
//		System.out.println("ADRIAN 33333");
//
//		System.out.println(decode);
		byte[] rez = Base64.decodeBase64(decode);
		return rez;

	}

	@GetMapping(value = "/newsImage/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getNewsImageAsByteArrayForHome(@PathVariable(name = "fileName") final String fileName)
			throws IOException {
		List<NewsSiteData> images = siteModel.getNewsSiteData();
		Optional<NewsSiteData> wrappedImage = images.stream().filter(e -> e.getId().equals(fileName)).findFirst();
		String currentImage = wrappedImage.isPresent() ? wrappedImage.get().getImage() : Constants.TEMPLATE_IMAGE_DATA;

		if (wrappedImage.isEmpty() || StringUtils.isEmpty(wrappedImage.get().getImage())) {
			currentImage = Constants.TEMPLATE_IMAGE_DATA;
		}

		String[] imageParts = currentImage.split(",");
		String image = imageParts[0];
		if (imageParts.length > 1) {
			image = imageParts[1];

		} else {
			imageParts = currentImage.split("%2C");
			if (imageParts.length > 1) {
				image = imageParts[1];
			}
		}

		String decode = UriUtils.decode(image, "UTF-8");

		byte[] rez = Base64.decodeBase64(decode);
		return rez;

	}

	@GetMapping(value = "/admin/image-news-upload/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getImageAsByteArray(@PathVariable(name = "fileName") final String fileName)
			throws IOException {
		String currentImage = siteModel.getCurrentNews().getImage();

		if (StringUtils.isEmpty(currentImage)) {
			currentImage = Constants.TEMPLATE_IMAGE_DATA;
		}

		String[] imageParts = currentImage.split(",");
		String image = imageParts[0];
		if (imageParts.length > 1) {
			image = imageParts[1];

		} else {
			imageParts = currentImage.split("%2C");
			if (imageParts.length > 1) {
				image = imageParts[1];
			}
		}

		String decode = UriUtils.decode(image, "UTF-8");

		byte[] rez = Base64.decodeBase64(decode);
		return rez;
	}

	@GetMapping(value = "/admin/image-images-upload/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getMainImageAsByteArray(@PathVariable(name = "fileName") final String fileName)
			throws IOException {
		String currentImage = siteModel.getCurrentImages().getImage();
		if (StringUtils.isEmpty(currentImage)) {
			currentImage = Constants.TEMPLATE_IMAGE_DATA;
		}

		String[] imageParts = currentImage.split(",");

		String image = imageParts[0];
		if (imageParts.length > 1) {
			image = imageParts[1];

		} else {
			imageParts = currentImage.split("%2C");
			if (imageParts.length > 1) {
				image = imageParts[1];
			}
		}

		String decode = UriUtils.decode(image, "UTF-8");

		byte[] rez = Base64.decodeBase64(decode);
		return rez;
	}

}
