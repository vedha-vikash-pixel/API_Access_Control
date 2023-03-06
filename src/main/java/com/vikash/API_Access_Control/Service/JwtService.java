package com.vikash.API_Access_Control.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {          //service for handling JWTs

	@Value("${jwt.secret}")
	private String SECRET_KEY;         //secret key from configuration
	private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<String,Object>(),userDetails);
	}

	// Generates a token for the given user details and returns it as a string
	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		logger.info("Generating JWT for user '{}'", userDetails.getUsername());
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 24)))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	// Extracts the username from the given token and returns it as a string
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// Extracts the claim of the given function from the given token and returns it as an object
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// Extracts all claims from the given token and returns them as a Claims object
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	// Returns the signing key for JWTs
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	// Checks if the given token is valid for the given user details
	public boolean isTokenValid(String token,UserDetails userDetails) {
		String userName = extractUserName(token);
		if(userName.equals(userDetails.getUsername()) && !isExpired(token))
			return true;
		return false;
	}

	// Checks if the given token is expired
	private boolean isExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
}
