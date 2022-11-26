package com.adrian.school_site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SchoolSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolSiteApplication.class, args);
	}

}
