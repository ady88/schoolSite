package com.adrian.school_site.controllers;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.adrian.school_site.model.OtherSiteData;
import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.utils.Constants;

@RestController
public class ImageBackgroundController {
	@Autowired
	private SiteDataModel siteModel;

	@GetMapping(value = "/image/background/top", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getImageAsByteArrayForTop() throws IOException {
		OtherSiteData otherData = siteModel.getOtherSiteData();

		String currentImage = otherData.getTopImage() != null ? otherData.getTopImage() : Constants.TEMPLATE_IMAGE_DATA;

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

	@GetMapping(value = "/image/background/main", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getImageAsByteArrayForMain() throws IOException {
		OtherSiteData otherData = siteModel.getOtherSiteData();

		String currentImage = otherData.getMainImage() != null ? otherData.getMainImage()
				: Constants.TEMPLATE_IMAGE_DATA;

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
