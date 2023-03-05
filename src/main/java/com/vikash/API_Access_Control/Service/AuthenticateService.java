package com.vikash.API_Access_Control.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vikash.API_Access_Control.Entity.User;
import com.vikash.API_Access_Control.Repository.UserRepository;
import com.vikash.API_Access_Control.Utils.AuthenticationRequest;
import com.vikash.API_Access_Control.Utils.AuthenticationResponse;
import com.vikash.API_Access_Control.Utils.RegisterRequest;
import com.vikash.API_Access_Control.Utils.Role;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Service
public class AuthenticateService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder().username(request.getUserName())
				.password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).build();
		repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		var user = repository.findByUsername(request.getUserName())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

}
