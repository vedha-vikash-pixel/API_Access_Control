package com.vikash.API_Access_Control.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myapp/private")
public class PrivateController {

	@GetMapping
	public ResponseEntity<String> publicAPI() {
		return ResponseEntity.ok("Hello from a private page");
	}
	
}
