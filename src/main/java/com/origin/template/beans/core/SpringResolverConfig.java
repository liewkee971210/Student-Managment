package com.origin.template.beans.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import io.swagger.v3.core.jackson.ModelResolver;

@Configuration
public class SpringResolverConfig {
  @Bean
  public ModelResolver modelResolver(ObjectMapper objectMapper) {
    return new ModelResolver(objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE));
  }
}