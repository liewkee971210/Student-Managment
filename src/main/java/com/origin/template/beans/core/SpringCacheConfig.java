package com.origin.template.beans.core;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SpringCacheConfig {
  @Primary
  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager();
  }
}
