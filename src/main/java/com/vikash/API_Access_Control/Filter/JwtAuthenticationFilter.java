package com.vikash.API_Access_Control.Filter;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vikash.API_Access_Control.Service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService JwtService;
	@Autowired
	private final UserDetailsService userDetailsService;
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException { 
		logger.debug("Processing request in JwtAuthenticationFilter...");
		
		final String authHeader = request.getHeader("Authorization");		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {    // Not Authorized user request passed to the next filter
			logger.debug("Authorization header not found or invalid. Passing request to the next filter...");
			filterChain.doFilter(request, response);
			return;
		}
		final String jwt = authHeader.substring(7);
		final String userName = JwtService.extractUserName(jwt);		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null) {   // Authentication successful from token 
			logger.debug("User authenticated successfully from token. Creating new authentication and storing it in SecurityContextHolder...");
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);					      
		      if (JwtService.isTokenValid(jwt, userDetails)) {
		        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
		            userDetails,
		            null,
		            userDetails.getAuthorities()
		        );
		        authToken.setDetails(
		            new WebAuthenticationDetailsSource().buildDetails(request)
		        );
		        SecurityContextHolder.getContext().setAuthentication(authToken);    //creating new Authentication and storing it in SecurityContextHolder		        
		}
	}
		
		  
		logger.debug("Request processed in JwtAuthenticationFilter. Passing request to the next filter...");
		filterChain.doFilter(request, response);
	}
}