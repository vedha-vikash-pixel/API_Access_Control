package com.vikash.API_Access_Control.Controller;

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
public class PublicController {
	
	@Autowired
	private AuthenticateService authenticationService; 
	
	@GetMapping
	public ResponseEntity<String> publicAPI() {
		return ResponseEntity.ok("Hello from a public page");
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authenticationService.register(request));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {		
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
	
}
