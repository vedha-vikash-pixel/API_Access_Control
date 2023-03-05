package com.vikash.API_Access_Control.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.vikash.API_Access_Control.Filter.JwtAuthenticationFilter;

public class SecurityConfig {

	  @Autowired
	  private JwtAuthenticationFilter jwtAuthFilter;
	  @Autowired
	  private AuthenticationProvider authenticationProvider;
	  @Autowired
	  private LogoutHandler logoutHandler;

	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf()
	        .disable()
	        .authorizeHttpRequests()
	        .requestMatchers("/public/**")
	          .permitAll()
	        .anyRequest()
	          .authenticated()
	        .and()
	          .sessionManagement()
	          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        .logout()
	        .logoutUrl("/api/v1/auth/logout")
	        .addLogoutHandler(logoutHandler)
	        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());	    

	    return http.build();
	  }
	
}
