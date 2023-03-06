package com.vikash.API_Access_Control.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myapp/private")
public class PrivateController { // Controller with private endpoints

	private static final Logger logger = LoggerFactory.getLogger(PrivateController.class);
	
	@GetMapping
	public ResponseEntity<String> privateAPI() {	 //private endpoint
		logger.info("Accessed privateAPI endpoint");	
		return ResponseEntity.ok("Private page which requires authentication");
	}

	
	@GetMapping("/admin")                    //private admin page which user cannot access
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> privateAdminAPI() {
		logger.info("Accessed admin page");
		return ResponseEntity.ok("Private page which can be accessed only by Admin");
	}

	
	@GetMapping("/user")                    //private user page which admin can also access
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<String> privateUserAPI() {
		logger.info("Accessed user page");
		return ResponseEntity.ok("Private page which can be accessed by Admin and user");
	}

}
