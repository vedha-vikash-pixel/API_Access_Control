package com.vikash.API_Access_Control.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vikash.API_Access_Control.Repository.UserRepository;

@Configuration
public class ApplicationConfig {

	@Autowired
	private UserRepository userRepository;
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

	@Bean
	public UserDetailsService userDetailsService() {
		logger.debug("Creating user details service bean...");
		return username -> userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Registered"));
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		logger.debug("Creating authentication provider bean...");
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		logger.debug("Creating authentication manager bean...");
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		logger.debug("Creating password encoder bean...");
		return new BCryptPasswordEncoder();
	}

}
