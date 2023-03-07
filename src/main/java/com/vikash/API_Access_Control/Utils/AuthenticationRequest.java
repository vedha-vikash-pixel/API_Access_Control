package com.vikash.API_Access_Control.Utils;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationRequest {   //class modal for authentication requests with properties

	@NotBlank(message = "Username is invalid")	
	private String userName;
	
	@NotBlank(message = "password is invalid")
	private String password;
	
}
