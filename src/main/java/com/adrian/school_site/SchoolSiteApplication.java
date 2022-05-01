package com.adrian.school_site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SchoolSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolSiteApplication.class, args);
	}

}
