package com.vikash.API_Access_Control.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vikash.API_Access_Control.Service.AuthenticateService;
import com.vikash.API_Access_Control.Utils.AuthenticationExceptionResponse;
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
		logger.info("Accessed publicAPI endpoint");		
		return ResponseEntity.ok("Public page which doesn't require authentication");
	}
	
	@PostMapping("/auth/register")         //register endpoint
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		logger.info("Received registration request for user {}", request.getUserName());
		try {
			AuthenticationResponse response = authenticationService.register(request);
	        return ResponseEntity.ok(response);
	    } catch (ResponseStatusException e) {	    	
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                             .body(new AuthenticationExceptionResponse(e.getMessage()));
	    }
	}

	@PostMapping("/auth/login")           //login endpoint
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
		logger.info("Received registration request for user {}", request.getUserName());
		try {
			AuthenticationResponse response = authenticationService.authenticate(request);
			return ResponseEntity.ok(response);
		}catch (ResponseStatusException e) {			
			return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new AuthenticationExceptionResponse(e.getMessage()));
		}
		
	}
	
}
