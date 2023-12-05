package com.betrybe.agrix.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class SecurityFilterConfig.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityFilterConfig {

  private final SecurityFilter securityFilter;

  public SecurityFilterConfig(SecurityFilter securityFilter) {
    this.securityFilter = securityFilter;
  }

  /**
  * Bean.
  */
  @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
             .csrf(AbstractHttpConfigurer::disable).sessionManagement(session -> session
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             .authorizeHttpRequests(authorize -> authorize
             .requestMatchers(HttpMethod.POST, "/persons").permitAll()
             .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
             .anyRequest().authenticated())
             .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
             .build();
  }
}