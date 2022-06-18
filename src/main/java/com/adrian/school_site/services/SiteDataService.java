package com.adrian.school_site.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.adrian.school_site.entity.GeneralData;
import com.adrian.school_site.entity.NewsData;
import com.adrian.school_site.entity.PageData;
import com.adrian.school_site.model.CodeText;
import com.adrian.school_site.model.GeneralSiteModel;
import com.adrian.school_site.model.NewsSiteData;
import com.adrian.school_site.model.Page;
import com.adrian.school_site.model.PageSiteData;
import com.adrian.school_site.model.SiteDataModel;
import com.adrian.school_site.repositories.GeneralSiteDataRepository;
import com.adrian.school_site.repositories.NewsDataRepository;
import com.adrian.school_site.repositories.PageDataRepository;
import com.adrian.school_site.utils.Constants;

/**
 * Service used for handling site data models.
 */
@Service
public class SiteDataService {

	private static final Logger LOG = Logger.getLogger(SiteDataService.class.getName());

	@Autowired
	private GeneralSiteDataRepository generalSiteDataRepository;

	@Autowired
	private PageDataRepository pageDataRepository;

	@Autowired
	private NewsDataRepository newsDataRepository;

	/**
	 * Gets the general data of the site.
	 * 
	 * @return general view data
	 */
	public GeneralSiteModel getGeneralSiteData() {
		final GeneralData result = generalSiteDataRepository.findById(1).get();

		return convertFromEntity(result);
	}

	/**
	 * Gets the page related data of the site.
	 * 
	 * @return the page related data of the site
	 */
	public List<PageSiteData> getAllPageData() {
		Iterable<PageData> repositoryData = pageDataRepository.findAll();
		List<PageSiteData> result = new ArrayList<>();
		for (PageData page : repositoryData) {
			Page pageEnum = Page.fromInt(page.getId()).get();
			PageSiteData siteData = convertFromEntity(pageEnum, page);
			result.add(siteData);
		}
		result.sort(Comparator.comparing(PageSiteData::getInfo));
		return result;
	}

	public List<NewsSiteData> getAllNewsData() {
		Iterable<NewsData> repositoryData = newsDataRepository.findAll();
		List<NewsSiteData> result = new ArrayList<>();
		for (NewsData page : repositoryData) {
			NewsSiteData siteData = convertFromEntity(page);
			result.add(siteData);
		}
		result.sort(Comparator.comparing(NewsSiteData::getDate));
		return result;
	}
	
	public NewsSiteData getNewsDataByTitle(final String title) {
		Optional<NewsData> newsData = newsDataRepository.findByTitle(title);
		NewsSiteData result = new NewsSiteData();
		
		if (newsData.isPresent()) {
			result = convertFromEntity(newsData.get());
		}
		
		return result;
	}

	/**
	 * Saves the form data from the admin page to the database.
	 * 
	 * @param model session data to be saved
	 */
	public void saveGeneralSiteData(SiteDataModel model) {
		// get existing data
		final GeneralData result = generalSiteDataRepository.findById(1).get();
		GeneralSiteModel generalSiteModel = model.getGeneralSiteModel();
		result.setAdress(generalSiteModel.getAdress());
		result.setEmail(generalSiteModel.getEmail());
		result.setMotto(generalSiteModel.getMotto());
		result.setPhone(generalSiteModel.getPhone());
		result.setSitename(generalSiteModel.getName());
		generalSiteDataRepository.save(result);

		final Iterable<PageData> pageResults = pageDataRepository.findAll();
		for (PageData pageData : pageResults) {
			switch (pageData.getId()) {
			case 1:
				pageData.setFeature1(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.MAIN))
						.findFirst().get().getFeature1());
				pageData.setFeature2(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.MAIN))
						.findFirst().get().getFeature2());
				pageData.setName(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.MAIN)).findFirst()
						.get().getName());
				pageData.setId(Page.MAIN.getId());
				break;
			case 2:
				pageData.setFeature1(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.SECONDARY))
						.findFirst().get().getFeature1());
				pageData.setFeature2(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.SECONDARY))
						.findFirst().get().getFeature2());
				pageData.setName(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.SECONDARY))
						.findFirst().get().getName());
				pageData.setId(Page.SECONDARY.getId());
				break;
			case 3:
				pageData.setFeature1(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.THIRD))
						.findFirst().get().getFeature1());
				pageData.setFeature2(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.THIRD))
						.findFirst().get().getFeature2());
				pageData.setName(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.THIRD))
						.findFirst().get().getName());
				pageData.setId(Page.THIRD.getId());
				break;
			case 4:
				pageData.setFeature1(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.FORTH))
						.findFirst().get().getFeature1());
				pageData.setFeature2(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.FORTH))
						.findFirst().get().getFeature2());
				pageData.setName(model.getPageSiteData().stream().filter(e -> e.getInfo().equals(Page.FORTH))
						.findFirst().get().getName());
				pageData.setId(Page.FORTH.getId());
				break;
			default:
				LOG.severe("Unexpected page id.");

			}
			pageDataRepository.saveAll(pageResults);

		}
	}

	/**
	 * Saves the form data from the admin page to the database.
	 * 
	 * @param model session data to be saved
	 */
	public CodeText saveNewsSiteData(SiteDataModel formSiteModel) {
		NewsSiteData data = formSiteModel.getCurrentNews();
		NewsData dataDb = convertFromEntity(data);
		
		if (StringUtils.isEmpty( dataDb.getTitle())) {
			return new CodeText(2, "'Title' cannot be empty.");
		}
		
		try {
			newsDataRepository.save(dataDb);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException t) {
				System.out.println(t.getCause().getMessage());
				return new CodeText(1, t.getCause().getMessage());
			}

		}

		return new CodeText(0, Constants.SAVE_SUCCESS_MESSAGE);
	}

	private NewsData convertFromEntity(NewsSiteData data) {
		NewsData newsDb = new NewsData();
		newsDb.setDate(data.getDate());
		newsDb.setTitle(data.getTitle());
		newsDb.setDescription(data.getDescription());
		newsDb.setLinklabel(data.getLinkLabel());
		newsDb.setLinkurl(data.getLinkUrl());
		newsDb.setPageId(data.getInfo().getId());
		newsDb.setImage(Base64.getDecoder().decode(data.getImage()));
		return newsDb;
	}

	private NewsSiteData convertFromEntity(NewsData data) {
		NewsSiteData news = new NewsSiteData();
		news.setDate(data.getDate());
		news.setTitle(data.getTitle());
		news.setDescription(data.getDescription());
		news.setLinkLabel(data.getLinklabel());
		news.setLinkUrl(data.getLinkurl());
		news.setInfo(Page.fromInt(data.getPageId()).get());
		news.setImage(Base64.getEncoder().encodeToString(data.getImage()));
		return news;
	}

	private GeneralSiteModel convertFromEntity(GeneralData entity) {
		GeneralSiteModel result = new GeneralSiteModel();
		result.setAdress(entity.getAdress());
		result.setName(entity.getSitename());
		result.setEmail(entity.getEmail());
		result.setPhone(entity.getPhone());
		result.setMotto(entity.getMotto());

		return result;
	}

	private PageSiteData convertFromEntity(Page pageInfo, PageData entity) {
		PageSiteData result = new PageSiteData(pageInfo);
		result.setFeature1(entity.getFeature1());
		result.setFeature2(entity.getFeature2());
		result.setName(entity.getName());
		return result;
	}
}
