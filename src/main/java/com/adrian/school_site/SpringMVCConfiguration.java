package com.adrian.school_site;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

//		registry.addResourceHandler("/**/images/{filename:\\w+\\.png}").addResourceLocations("classpath:/static/")
//				.setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));

		registry.addResourceHandler("/**/imgs/*.png").addResourceLocations("classpath:/static/")
				.setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));

		registry.addResourceHandler("/**/imgs/*.jpg").addResourceLocations("classpath:/static/")
				.setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));

		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/")
				.setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS)).resourceChain(true);
	}
}
