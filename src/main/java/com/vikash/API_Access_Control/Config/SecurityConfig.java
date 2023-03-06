package com.vikash.API_Access_Control.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vikash.API_Access_Control.Filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	  
	  private final JwtAuthenticationFilter jwtAuthFilter;
	  private final AuthenticationProvider authenticationProvider;

	  private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class); 
	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {   //filter chain bean for incoming requests
		  
		  logger.debug("Creating security filter chain...");
		  http
	        .csrf()
	        .disable()
	        .authorizeHttpRequests()
	        .requestMatchers("/myapp/public/**")
	        .permitAll()	        
	        .anyRequest()
	        .authenticated()
//	        .requestMatchers("/myapp/private/**")
//	        .hasRole("[ADMIN]")
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);  

		  logger.debug("Security filter chain created successfully.");  
	    return http.build();
	  }
	
}
