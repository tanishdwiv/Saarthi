package com.jobportal.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {
	private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

	// Token expiration time in milliseconds (e.g., 1 hour = 3600000 ms)
	private static final long JWT_TOKEN_VALIDITY = 36000000;

	// Retrieve username (or subject) from the JWT token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Retrieve expiration date from the JWT token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// Retrieve any claim from the JWT token
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// Get all claims from the token using the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// Check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// Generate a token for the user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		CustomUserDetails customUser=(CustomUserDetails)userDetails;
		claims.put("id", customUser.getId());
		claims.put("name", customUser.getName());
		claims.put("accountType", customUser.getAccountType());
		claims.put("profileId", customUser.getProfileId());
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// Create the token by signing it with the secret key
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// Validate the token
	public Boolean validateToken(String token, String username) {
		final String tokenUsername = getUsernameFromToken(token);
		return (tokenUsername.equals(username) && !isTokenExpired(token));
	}
}
