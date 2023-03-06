package com.vikash.API_Access_Control.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationRequest {   //class modal for authentication requests with properties

	private String userName;
	
	private String password;
	
}
