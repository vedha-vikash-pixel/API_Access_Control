package com.vikash.API_Access_Control.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myapp")
public class PublicController {

	@GetMapping("/public")
	public String publicAPI() {
		return "Public Page";
	}
	
}
