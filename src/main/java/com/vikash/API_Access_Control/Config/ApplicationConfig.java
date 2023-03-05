package com.vikash.API_Access_Control.Config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vikash.API_Access_Control.Repository.UserRepository;

@Configuration
public class ApplicationConfig {
	
	private UserRepository userRepository;

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Registered"));
	}
	
}
