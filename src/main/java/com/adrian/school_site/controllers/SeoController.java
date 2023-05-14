package com.adrian.school_site.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adrian.school_site.model.seo.XmlUrl;
import com.adrian.school_site.model.seo.XmlUrlSet;

@Controller
public class SeoController {

	private List<String> URLS = List.of("/home", "/resurse", "/personal", "/contact");

	@Value("${sitemap.path: http://www.scoala-telesti.net/sitemap.xml}")
	private String sitemapPath;

	@Value("${domain.path: http://www.scoala-telesti.net}")
	private String domainPath;

	@GetMapping(value = { "/robots.txt", "/robot.txt" }, produces = { MediaType.TEXT_PLAIN_VALUE })
	@ResponseBody
	public String getRobotsTxt() {
		var result = """
				User-agent: *
				Disallow: /admin

				Sitemap: %s
				""";
		return String.format(result, sitemapPath);
	}

	@GetMapping(value = "/sitemap.xml", produces = { MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public XmlUrlSet main() {
		XmlUrlSet xmlUrlSet = new XmlUrlSet();
		for (String eachLink : URLS) {
			create(xmlUrlSet, eachLink, XmlUrl.Priority.HIGH);
		}
		return xmlUrlSet;
	}

	private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
		xmlUrlSet.addUrl(new XmlUrl(domainPath + link, priority));
	}
}
