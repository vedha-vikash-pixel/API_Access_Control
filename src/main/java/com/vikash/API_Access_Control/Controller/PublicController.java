package com.vikash.API_Access_Control.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.API_Access_Control.Service.AuthenticateService;
import com.vikash.API_Access_Control.Utils.AuthenticationRequest;
import com.vikash.API_Access_Control.Utils.AuthenticationResponse;
import com.vikash.API_Access_Control.Utils.RegisterRequest;

@RestController
@RequestMapping("/myapp/public")
public class PublicController {    // Controller with public endpoints
	
	private static final Logger logger = LoggerFactory.getLogger(PublicController.class);
	
	@Autowired
	private AuthenticateService authenticationService; 
	
	@GetMapping							//public endpoint
	public ResponseEntity<String> publicAPI() {		
		logger.info("Accessed publicAPI() endpoint");
		return ResponseEntity.ok("Public page which doesn't require authentication");
	}
	
	@PostMapping("/auth/register")         //register endpoint
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		logger.info("Received registration request for user {}", request.getUserName());
		return ResponseEntity.ok(authenticationService.register(request));
	}

	@PostMapping("/auth/login")           //login endpoint
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		logger.info("Received registration request for user {}", request.getUserName());
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
	
}
