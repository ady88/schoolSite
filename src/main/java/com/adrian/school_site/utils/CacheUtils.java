package com.adrian.school_site.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Component that holds helper methods for manipulating cached data.
 * 
 */
@Component
public class CacheUtils {
	@Autowired
	CacheManager cacheManager;

	/**
	 * Evicts all values of specified cache
	 * 
	 * @param cacheName
	 */
	public void evictAllCacheValues(String cacheName) {
		cacheManager.getCache(cacheName).clear();
		
	}
	
	/**
	 * Evicts all values of all caches
	 * 
	 * @param cacheName
	 */
	public void evictAllCacheValues() {
		for (String cacheName: cacheManager.getCacheNames()) {
			cacheManager.getCache(cacheName).clear();
		}
	}
}
