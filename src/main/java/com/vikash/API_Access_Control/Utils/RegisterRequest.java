package com.vikash.API_Access_Control.Utils;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest { // class modal for register requests with properties

	@NotBlank(message = "Username is invalid")
	private String userName;

	@NotBlank(message = "password is invalid")
	private String password;

	@NotNull(message = "Role is invalid")
	@Enumerated(EnumType.STRING)
	private Role role;
}