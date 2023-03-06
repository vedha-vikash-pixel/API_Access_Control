package com.vikash.API_Access_Control.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myapp/private")
public class PrivateController {        // Controller with private endpoints

	@GetMapping
	public ResponseEntity<String> privateAPI() {		
		return ResponseEntity.ok("Private page which requires authentication");
	}
	
	@GetMapping("/admin")
	public ResponseEntity<String> privateAdminAPI() {		
		return ResponseEntity.ok("Private page which can be accessed only by Admin");
	}
	
	@GetMapping("/user")
	public ResponseEntity<String> privateUserAPI() {		
		return ResponseEntity.ok("Private page which can be accessed by Admin and user");
	}
	
}
