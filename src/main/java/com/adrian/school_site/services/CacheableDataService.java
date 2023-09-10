package com.adrian.school_site.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.adrian.school_site.entity.GeneralData;
import com.adrian.school_site.entity.ImagesData;
import com.adrian.school_site.entity.NewsData;
import com.adrian.school_site.entity.OtherData;
import com.adrian.school_site.entity.ResourcesData;
import com.adrian.school_site.entity.ShortNewsData;
import com.adrian.school_site.entity.StaffData;
import com.adrian.school_site.model.CodeText;
import com.adrian.school_site.model.GeneralSiteModel;
import com.adrian.school_site.model.ImagesSiteData;
import com.adrian.school_site.model.NewsSiteData;
import com.adrian.school_site.model.OtherSiteData;
import com.adrian.school_site.model.Page;
import com.adrian.school_site.model.PageSiteData;
import com.adrian.school_site.model.ResourcesSiteData;
import com.adrian.school_site.model.ShortNewsSiteData;
import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.model.StaffSiteData;
import com.adrian.school_site.repositories.GeneralSiteDataRepository;
import com.adrian.school_site.repositories.ImagesDataRepository;
import com.adrian.school_site.repositories.NewsDataRepository;
import com.adrian.school_site.repositories.OtherDataRepository;
import com.adrian.school_site.repositories.ResourcesDataRepository;
import com.adrian.school_site.repositories.ShortNewsSiteDataRepository;
import com.adrian.school_site.repositories.StaffDataRepository;
import com.adrian.school_site.utils.CacheUtils;
import com.adrian.school_site.utils.Constants;

/**
 * Service used for handling site data models.
 */
@Service
public class CacheableDataService {

	private static final Logger LOG = Logger.getLogger(CacheableDataService.class.getName());

	@Autowired
	private GeneralSiteDataRepository generalSiteDataRepository;
	
	@Autowired
	private OtherDataRepository otherSiteDataRepository;

	@Autowired
	private NewsDataRepository newsDataRepository;

	@Autowired
	private ShortNewsSiteDataRepository shortNewsDataRepository;

	@Autowired
	private ImagesDataRepository imagesDataRepository;

	@Autowired
	private StaffDataRepository staffDataRepository;

	@Autowired
	private ResourcesDataRepository resourcesDataRepository;

	@Autowired
	private CacheUtils cacheUtils;

	/**
	 * Gets the general data of the site.
	 * 
	 * @return general view data
	 */
	@Cacheable(cacheNames = { "general" })
	public GeneralSiteModel getGeneralSiteData() {
		final GeneralData result = generalSiteDataRepository.findAll().iterator().next();

		return convertFromEntity(result);
	}
	
	/**
	 * Gets the other data of the site.
	 * 
	 * @return general view data
	 */
	@Cacheable(cacheNames = { "other" })
	public OtherSiteData getOtherSiteData() {
		final OtherData result = otherSiteDataRepository.findAll().iterator().next();
		
		if (result.getUseMainImage() == null) {
			result.setUseMainImage(false);
		}
		
		if (result.getUseTopImage() == null) {
			result.setUseTopImage(false);
		}
		
		return convertFromEntity(result);
	}

	/**
	 * Gets the page related data of the site.
	 * 
	 * @return the page related data of the site
	 */
	@Cacheable(cacheNames = { "pages" })
	public List<PageSiteData> getAllPageData() {
		GeneralData generalSiteData = generalSiteDataRepository.findAll().iterator().next();
		List<PageSiteData> result = new ArrayList<>();
		Page homePage = Page.fromInt(1).get();
		PageSiteData homePageData = convertFromEntity(homePage, generalSiteData);
		result.add(homePageData);

		Page resourcesPage = Page.fromInt(2).get();
		PageSiteData resourcesPageData = convertFromEntity(resourcesPage, generalSiteData);
		result.add(resourcesPageData);

		Page staffPage = Page.fromInt(3).get();
		PageSiteData staffPageData = convertFromEntity(staffPage, generalSiteData);
		result.add(staffPageData);

		Page aboutPage = Page.fromInt(4).get();
		PageSiteData aboutPageData = convertFromEntity(aboutPage, generalSiteData);
		result.add(aboutPageData);

		result.sort(Comparator.comparing(PageSiteData::getInfo));
		return result;
	}

	@Cacheable(cacheNames = { "news" })
	public List<NewsSiteData> getAllNewsData() {
		Iterable<NewsData> repositoryData = newsDataRepository.findAll();
		List<NewsSiteData> result = new ArrayList<>();
		for (NewsData page : repositoryData) {
			NewsSiteData siteData = convertFromEntity(page);
			result.add(siteData);
		}
		result.sort(Comparator.comparing(NewsSiteData::getOrder, Comparator.reverseOrder()));
		return result;
	}

	@Cacheable(cacheNames = { "shortNews" })
	public List<ShortNewsSiteData> getAllShortNewsData() {
		Iterable<ShortNewsData> repositoryData = shortNewsDataRepository.findAll();
		List<ShortNewsSiteData> result = new ArrayList<>();
		for (ShortNewsData page : repositoryData) {
			ShortNewsSiteData siteData = convertFromEntity(page);
			result.add(siteData);
		}
		result.sort(Comparator.comparing(ShortNewsSiteData::getOrder, Comparator.reverseOrder()));
		return result;
	}

	@Cacheable(cacheNames = { "resources" })
	public List<ResourcesSiteData> getAllResourcesData() {
		Iterable<ResourcesData> repositoryData = resourcesDataRepository.findAll();
		List<ResourcesSiteData> result = new ArrayList<>();
		for (ResourcesData page : repositoryData) {
			ResourcesSiteData siteData = convertFromEntity(page);
			result.add(siteData);
		}
		result.sort(Comparator.comparing(ResourcesSiteData::getResourceDate).reversed());
		return result;
	}

	@Cacheable(cacheNames = { "images" })
	public List<ImagesSiteData> getAllImagesData() {
		Iterable<ImagesData> repositoryData = imagesDataRepository.findAll();
		List<ImagesSiteData> result = new ArrayList<>();
		for (ImagesData page : repositoryData) {
			ImagesSiteData siteData = convertFromEntity(page);
			result.add(siteData);
		}
		result.sort(Comparator.comparing(ImagesSiteData::getOrder));
		return result;
	}

	@Cacheable(cacheNames = { "staff" })
	public List<StaffSiteData> getAllStaffData() {
		Iterable<StaffData> repositoryData = staffDataRepository.findAll();
		List<StaffSiteData> result = new ArrayList<>();
		for (StaffData page : repositoryData) {
			StaffSiteData siteData = convertFromEntity(page);
			result.add(siteData);
		}
		result.sort(Comparator.comparing(StaffSiteData::getOrder));
		return result;
	}

	/**
	 * Saves the form data from the admin page to the database.
	 * 
	 * @param model session data to be saved
	 */
	@Transactional
	public CodeText saveNewsSiteData(SiteDataModel formSiteModel) {
		NewsSiteData data = formSiteModel.getCurrentNews();
		NewsData dataDb = convertFromEntity(data);

		if (StringUtils.isEmpty(dataDb.getTitle())) {
			return new CodeText(2, "'Title' cannot be empty.");
		}

		// if news item already exists then remove it and re-add it
		Optional<NewsData> existingNews = newsDataRepository.findByTitle(data.getTitle());

		if (existingNews.isPresent() && StringUtils.isNotEmpty(existingNews.get().getTitle())) {
			NewsData newData = existingNews.get();
			newData.setDescription(data.getDescription());

			if (StringUtils.isNotEmpty(newData.getDescription())) {
				newData.setDescription(newData.getDescription().replace("---", "<br> &nbsp;&nbsp;&nbsp;&nbsp; -"));
			}

			newData.setTitle(data.getTitle());
			newData.setOrder(data.getOrder());
			newData.setLinklabel(data.getLinkLabel());
			newData.setLinkurl(data.getLinkUrl());
			newsDataRepository.save(newData);
			cacheUtils.evictAllCacheValues("news");
			return new CodeText(0, Constants.SAVE_SUCCESS_MESSAGE);
		}

		try {
			newsDataRepository.save(dataDb);
			cacheUtils.evictAllCacheValues("news");
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException t) {
				System.out.println(t.getCause().getMessage());
				return new CodeText(1, t.getCause().getMessage());
			}

		}

		return new CodeText(0, Constants.SAVE_SUCCESS_MESSAGE);
	}

	/**
	 * Saves the form data from the admin page to the database.
	 * 
	 * @param model session data to be saved
	 */
	public CodeText saveImagesSiteData(SiteDataModel formSiteModel) {
		ImagesSiteData data = formSiteModel.getCurrentImages();
		ImagesData dataDb = convertFromEntity(data);

		if (StringUtils.isEmpty(dataDb.getTitle())) {
			return new CodeText(2, "'Title' cannot be empty.");
		}

		try {
			imagesDataRepository.save(dataDb);
			cacheUtils.evictAllCacheValues("images");
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException t) {
				System.out.println(t.getCause().getMessage());
				return new CodeText(1, t.getCause().getMessage());
			}

		}

		return new CodeText(0, Constants.SAVE_SUCCESS_MESSAGE);
	}

	/**
	 * Saves the form data from the admin resource page to the database.
	 * 
	 * @param model session data to be saved
	 */
	public CodeText saveResourcesSiteData(SiteDataModel formSiteModel) {
		ResourcesSiteData data = formSiteModel.getCurrentResources();
		ResourcesData dataDb = convertFromEntity(data);

		if (StringUtils.isEmpty(dataDb.getName())) {
			return new CodeText(2, "'Title' cannot be empty.");
		}

		try {
			resourcesDataRepository.save(dataDb);
			cacheUtils.evictAllCacheValues("resources");
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException t) {
				System.out.println(t.getCause().getMessage());
				return new CodeText(1, t.getCause().getMessage());
			}

		}

		return new CodeText(0, Constants.SAVE_SUCCESS_MESSAGE);
	}

	/**
	 * Saves the form data from the admin staff page to the database.
	 * 
	 * @param model session data to be saved
	 */
	public CodeText saveStaffSiteData(SiteDataModel formSiteModel) {
		StaffSiteData data = formSiteModel.getCurrentStaff();
		StaffData dataDb = convertFromEntity(data);

		if (StringUtils.isEmpty(dataDb.getFirstname())) {
			return new CodeText(2, "'firstname' cannot be empty.");
		}

		if (StringUtils.isEmpty(dataDb.getLastname())) {
			return new CodeText(2, "'lastname' cannot be empty.");
		}

		// if staff item already exists then remove it and re-add it
		Optional<StaffData> existingStaff = staffDataRepository.findByFirstnameAndLastname(data.getFirstname(),
				data.getLastname());

		if (existingStaff.isPresent() && StringUtils.isNotEmpty(existingStaff.get().getFirstname())) {
			StaffData staffData = existingStaff.get();
			staffData.setFirstname(data.getFirstname());

			staffData.setLastname(data.getLastname());
			staffData.setOrder(data.getOrder());
			staffData.setJobtype(data.getJobtype());
			staffData.setUnitname(data.getUnitname());
			staffDataRepository.save(staffData);
			cacheUtils.evictAllCacheValues("staff");
			return new CodeText(0, Constants.SAVE_SUCCESS_MESSAGE);
		}

		try {
			staffDataRepository.save(dataDb);
			cacheUtils.evictAllCacheValues("staff");
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException t) {
				System.out.println(t.getCause().getMessage());
				return new CodeText(1, t.getCause().getMessage());
			}

		}

		return new CodeText(0, Constants.SAVE_SUCCESS_MESSAGE);
	}

	/**
	 * Update all cache entries
	 */
	public void evictAllCacheEntries() {
		cacheUtils.evictAllCacheValues();
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

		if (ObjectUtils.isEmpty(title)) {
			return new CodeText(2, "'Title' cannot be empty.");
		}

		try {
			Integer result = imagesDataRepository.deleteByTitle(title);
			LOG.info("Removed news with id " + result);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException t) {
				System.out.println(t.getCause().getMessage());
				return new CodeText(1, t.getCause().getMessage());
			}

		}

		cacheUtils.evictAllCacheValues("images");
		return new CodeText(0, Constants.DELETE_SUCCESS_MESSAGE);
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

		if (ObjectUtils.isEmpty(name)) {
			return new CodeText(2, "'Title' cannot be empty.");
		}

		try {
			Integer result = resourcesDataRepository.deleteByName(name);
			LOG.info("Removed news with id " + result);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException t) {
				System.out.println(t.getCause().getMessage());
				return new CodeText(1, t.getCause().getMessage());
			}

		}

		cacheUtils.evictAllCacheValues("resources");
		return new CodeText(0, Constants.DELETE_SUCCESS_MESSAGE);
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

		if (ObjectUtils.isEmpty(firstname)) {
			return new CodeText(2, "'firstname' cannot be empty.");
		}

		if (ObjectUtils.isEmpty(lastname)) {
			return new CodeText(2, "'lastname' cannot be empty.");
		}

		try {
			Integer result = staffDataRepository.deleteByFirstnameAndLastname(firstname, lastname);
			LOG.info("Removed news with id " + result);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException t) {
				System.out.println(t.getCause().getMessage());
				return new CodeText(1, t.getCause().getMessage());
			}

		}

		cacheUtils.evictAllCacheValues("staff");
		return new CodeText(0, Constants.DELETE_SUCCESS_MESSAGE);
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

		if (ObjectUtils.isEmpty(title)) {
			return new CodeText(2, "'Title' cannot be empty.");
		}

		try {
			Integer result = newsDataRepository.deleteByTitle(title);
			LOG.info("Removed news with id " + result);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException t) {
				System.out.println(t.getCause().getMessage());
				return new CodeText(1, t.getCause().getMessage());
			}

		}
		cacheUtils.evictAllCacheValues("news");
		return new CodeText(0, Constants.DELETE_SUCCESS_MESSAGE);
	}

	private ResourcesSiteData convertFromEntity(ResourcesData data) {
		ResourcesSiteData resource = new ResourcesSiteData();
		resource.setDescription(data.getDescription());
		resource.setOrder(data.getOrder());
		resource.setName(data.getName());
		resource.setResourceDate(data.getResourceDate());
		resource.setDoc(Base64.getEncoder().encodeToString(data.getDoc()));
		resource.setDocContentType(data.getDocContentType());

		return resource;
	}

	private ResourcesData convertFromEntity(ResourcesSiteData data) {
		ResourcesData resourceDb = new ResourcesData();
		resourceDb.setDescription(data.getDescription());
		resourceDb.setOrder(data.getOrder());
		resourceDb.setName(data.getName());
		resourceDb.setResourceDate(data.getResourceDate());
		resourceDb.setDoc(Base64.getDecoder().decode(data.getDoc()));
		resourceDb.setDocContentType(data.getDocContentType());
		return resourceDb;
	}

	private StaffSiteData convertFromEntity(StaffData data) {
		StaffSiteData staff = new StaffSiteData();
		staff.setFirstname(data.getFirstname());
		staff.setOrder(data.getOrder());
		staff.setLastname(data.getLastname());
		staff.setJobtype(data.getJobtype());
		staff.setUnitname(data.getUnitname());

		return staff;
	}

	private StaffData convertFromEntity(StaffSiteData data) {
		StaffData staffDb = new StaffData();
		staffDb.setFirstname(data.getFirstname());
		staffDb.setOrder(data.getOrder());
		staffDb.setLastname(data.getLastname());
		staffDb.setJobtype(data.getJobtype());
		staffDb.setUnitname(data.getUnitname());
		return staffDb;
	}

	private ImagesSiteData convertFromEntity(ImagesData data) {
		ImagesSiteData image = new ImagesSiteData();
		image.setDescription(data.getDescription());
		image.setOrder(data.getOrder());
		image.setTitle(data.getTitle());
		image.setImage(Base64.getEncoder().encodeToString(data.getImage()));
		image.setImageContentType(data.getImageContentType());
		image.setOrder(data.getOrder());
		image.setId(Integer.toString(data.getId()));

		return image;
	}

	private ImagesData convertFromEntity(ImagesSiteData data) {
		ImagesData imagesDb = new ImagesData();
		imagesDb.setDescription(data.getDescription());
		imagesDb.setOrder(data.getOrder());
		imagesDb.setTitle(data.getTitle());
		imagesDb.setImage(Base64.getDecoder().decode(data.getImage()));
		imagesDb.setImageContentType(data.getImageContentType());
		return imagesDb;
	}

	private NewsData convertFromEntity(NewsSiteData data) {
		NewsData newsDb = new NewsData();
		newsDb.setTitle(data.getTitle());
		newsDb.setDescription(data.getDescription());
		newsDb.setLinklabel(data.getLinkLabel());
		newsDb.setLinkurl(data.getLinkUrl());
		newsDb.setImage(Base64.getDecoder().decode(data.getImage()));
		newsDb.setOrder(data.getOrder());
		newsDb.setImageContentType(data.getImageContentType());
		return newsDb;
	}

	private ShortNewsSiteData convertFromEntity(ShortNewsData data) {
		ShortNewsSiteData news = new ShortNewsSiteData();
		news.setLinkLabel(data.getTitle());
		news.setLinkUrl(data.getLinkUrl());
		news.setOrder(data.getOrder());

		return news;
	}

	private NewsSiteData convertFromEntity(NewsData data) {
		NewsSiteData news = new NewsSiteData();
		news.setTitle(data.getTitle());
		news.setDescription(data.getDescription());
		news.setLinkLabel(data.getLinklabel());
		news.setLinkUrl(data.getLinkurl());
		if (data.getImage() != null) {
			news.setImage(Base64.getEncoder().encodeToString(data.getImage()));
		}

		news.setId(Integer.toString(data.getId()));

		return news;
	}

	private OtherSiteData convertFromEntity(OtherData data) {
		OtherSiteData other = new OtherSiteData();
		
		if (data.getMainImage() != null) {
			other.setMainImage(Base64.getEncoder().encodeToString(data.getMainImage()));
		}
		
		if (data.getTopImage() != null) {
			other.setTopImage(Base64.getEncoder().encodeToString(data.getTopImage()));
		}
		
		other.setMainImageContentType(data.getMainImageContentType());
		other.setTopImageContentType(data.getTopImageContentType());
		other.setTheme(data.getTheme());
		other.setUseMainImage(data.getUseMainImage());
		other.setUseTopImage(data.getUseTopImage());

		other.setId(data.getId());

		return other;
	}
	
	private GeneralSiteModel convertFromEntity(GeneralData entity) {
		GeneralSiteModel result = new GeneralSiteModel();
		result.setAdress(entity.getAdress());
		result.setName(entity.getSitename());
		result.setEmail(entity.getEmail());
		result.setPhone(entity.getPhone());
		result.setMotto(entity.getMotto());
		result.setAboutPageName(entity.getAboutPageName());
		result.setHomePageName(entity.getHomePageName());
		result.setResourcePageName(entity.getResourcePageName());
		result.setStaffPageName(entity.getStaffPageName());
		result.setFacebookAddress(entity.getFacebookAddress());
		result.setStructure1(entity.getStructure1());
		result.setStructure2(entity.getStructure2());
		result.setStructure3(entity.getStructure3());
		result.setStructure4(entity.getStructure4());
		result.setMapUrl(entity.getMapUrl());
		result.setContactHeader(entity.getContactHeader());
		result.setStructuresHeader(entity.getStructuresHeader());

		return result;
	}

	private PageSiteData convertFromEntity(Page pageInfo, GeneralData entity) {
		PageSiteData result = new PageSiteData(pageInfo);
		result.setInfo(pageInfo);
		switch (pageInfo.getId()) {
		case 1:
			result.setName(entity.getHomePageName());
			break;
		case 2:
			result.setName(entity.getResourcePageName());
			break;
		case 3:
			result.setName(entity.getStaffPageName());
			break;
		case 4:
			result.setName(entity.getAboutPageName());
			break;
		default:
			LOG.severe("Unexpected page id.");

		}
		return result;
	}

}
