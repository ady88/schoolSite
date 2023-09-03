package com.adrian.school_site.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.school_site.model.CodeText;
import com.adrian.school_site.model.GeneralSiteModel;
import com.adrian.school_site.model.ImagesSiteData;
import com.adrian.school_site.model.NewsSiteData;
import com.adrian.school_site.model.PageSiteData;
import com.adrian.school_site.model.ResourcesSiteData;
import com.adrian.school_site.model.ShortNewsSiteData;
import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.model.StaffSiteData;

@Service
public class SiteDataService {

	@Autowired
	private CacheableDataService cacheService;

	/**
	 * Gets the general data of the site.
	 * 
	 * @return general view data
	 */
	public GeneralSiteModel getGeneralSiteData() {

		return cacheService.getGeneralSiteData();
	}
	
	/**
	 * Gets the page related data of the site.
	 * 
	 * @return the page related data of the site
	 */
	public List<PageSiteData> getAllPageData() {
		return cacheService.getAllPageData();
	}

	public List<NewsSiteData> getAllNewsData() {
		return cacheService.getAllNewsData();
	}
	
	public List<ShortNewsSiteData> getAllShortNewsData() {
		return cacheService.getAllShortNewsData();
	}

	public List<ResourcesSiteData> getAllResourcesData() {

		return cacheService.getAllResourcesData();
	}

	public List<ImagesSiteData> getAllImagesData() {
		return cacheService.getAllImagesData();
	}

	public List<StaffSiteData> getAllStaffData() {
		return cacheService.getAllStaffData();
	}

	public NewsSiteData getNewsDataByTitle(final String title) {
		Optional<NewsSiteData> newsData = cacheService.getAllNewsData().stream().filter(e -> e.getTitle().equals(title)).findFirst();

		return newsData.isPresent() ? newsData.get() : new NewsSiteData();
	}

	public ImagesSiteData getImagesDataByTitle(final String title) {
		Optional<ImagesSiteData> imagesData = cacheService.getAllImagesData().stream().filter(e -> e.getTitle().equals(title))
				.findFirst();

		return imagesData.isPresent() ? imagesData.get() : new ImagesSiteData();
	}

	public ResourcesSiteData getResourcesByTitle(final String name) {
		Optional<ResourcesSiteData> resourcesData = cacheService.getAllResourcesData().stream().filter(e -> e.getName().equals(name))
				.findFirst();

		return resourcesData.isPresent() ? resourcesData.get() : new ResourcesSiteData();
	}

	public StaffSiteData getStaffByName(final String firstname, final String lastname) {
		Optional<StaffSiteData> staffData = cacheService.getAllStaffData().stream().filter(e -> {
			return e.getFirstname().equals(firstname) && e.getLastname().equals(lastname);
		}).findFirst();

		return staffData.isPresent() ? staffData.get() : new StaffSiteData();
	}

	/**
	 * Saves the form data from the admin page to the database.
	 * 
	 * @param model session data to be saved
	 */
	public CodeText saveNewsSiteData(SiteDataModel formSiteModel) {

		return cacheService.saveNewsSiteData(formSiteModel);
	}

	/**
	 * Saves the form data from the admin page to the database.
	 * 
	 * @param model session data to be saved
	 */
	public CodeText saveImagesSiteData(SiteDataModel formSiteModel) {

		return cacheService.saveImagesSiteData(formSiteModel);
	}

	/**
	 * Saves the form data from the admin resource page to the database.
	 * 
	 * @param model session data to be saved
	 */
	public CodeText saveResourcesSiteData(SiteDataModel formSiteModel) {

		return cacheService.saveResourcesSiteData(formSiteModel);
	}

	/**
	 * Saves the form data from the admin staff page to the database.
	 * 
	 * @param model session data to be saved
	 */
	public CodeText saveStaffSiteData(SiteDataModel formSiteModel) {

		return cacheService.saveStaffSiteData(formSiteModel);
	}

	/**
	 * Deletes the images data by the given title information.
	 * 
	 * @param title
	 * @return {@link CodeText} object containg the result of the delete images
	 *         operation.
	 */
	@Transactional
	public CodeText deleteImagesSiteData(final String title) {

		return cacheService.deleteImagesSiteData(title);
	}

	/**
	 * Deletes the resources data by the given title information.
	 * 
	 * @param title
	 * @return {@link CodeText} object containg the result of the delete resources
	 *         operation.
	 */
	@Transactional
	public CodeText deleteResourcesSiteData(final String name) {

		return cacheService.deleteResourcesSiteData(name);
	}

	/**
	 * Deletes the staff data by the given title information.
	 * 
	 * @param title
	 * @return {@link CodeText} object containg the result of the delete resources
	 *         operation.
	 */
	@Transactional
	public CodeText deleteStaffSiteData(final String firstname, final String lastname) {

		return cacheService.deleteStaffSiteData(firstname, lastname);
	}

	/**
	 * Deletes the news data by the given title information.
	 * 
	 * @param title
	 * @return {@link CodeText} object containg the result of the delete news
	 *         operation.
	 */
	@Transactional
	public CodeText deleteNewsSiteData(final String title) {

		return cacheService.deleteNewsSiteData(title);
	}
}
