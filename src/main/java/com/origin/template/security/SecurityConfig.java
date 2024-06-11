package com.origin.template.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import com.origin.security.filter.OriginSecurityFilterConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Autowired
  private OriginSecurityFilterConfig originSecurityFilterConfig;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .headers(headers -> headers
        .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
      .csrf(csrf -> csrf.disable())
      .cors(cors -> cors.disable())
      .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(originSecurityFilterConfig, UsernamePasswordAuthenticationFilter.class)
      .authorizeHttpRequests(
          auth -> auth.anyRequest().permitAll());
    return httpSecurity.build();
  }
}
