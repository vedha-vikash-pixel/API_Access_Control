package com.vikash.API_Access_Control.Utils;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest { // class modal for register requests with properties

	
	private String userName;


	private String password;


	@Enumerated(EnumType.STRING)
	private Role role;
}