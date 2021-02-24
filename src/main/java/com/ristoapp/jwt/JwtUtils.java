package com.ristoapp.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ristoapp.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	@Value("${app.jwtAnonymousExpirationMs}")
	private int jwtAnonymousExpirationMs;

	public String generateJwtToken(Authentication authentication, boolean isAnonymous) {
		
		if (!isAnonymous){
			UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
			return Jwts.builder()
					.setSubject((userPrincipal.getUsername()))
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
					.signWith(SignatureAlgorithm.HS512, jwtSecret)
					.compact();
		}
		else {
			String userPrincipal = (String) authentication.getPrincipal();
			return Jwts.builder()
					.setSubject((userPrincipal))
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtAnonymousExpirationMs))
					.signWith(SignatureAlgorithm.HS512, jwtSecret)
					.compact();
		}
		
	}

	public String getUserNameFromJwtToken(String token) {
		if (token != null)
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		else
			return null;
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
