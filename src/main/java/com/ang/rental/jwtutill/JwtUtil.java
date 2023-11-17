package com.ang.rental.jwtutill;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

	private static final long serialVersionUID = 1L;

	@Value("${jwt.secret}")
	private String secret;

	public String extractUsername(String token) {
		SecurityDto securityDto = extractClaim(token);
		return securityDto.getUserName();
	}

	public SecurityDto extractClaim(String token) {
		log.info("[extractClaim] "+"Claims extracting started..");

		SecurityDto securityDto = new SecurityDto();
		Claims claims = extractAllClaims(token);
		securityDto.setExpDate(claims.getExpiration());
		securityDto.setUserName(claims.getSubject());

		log.info("[extractClaim] "+"Claims extracting end..");

		return securityDto;
	}

	private Claims extractAllClaims(String token){
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

	}

	public String generateToken(Authentication authentication, String username) {
		log.info("[generateToken] "+"Authorities assigning started..");

		Map<String, Object> claims = new HashMap<>();
		//This lambda expression is use to assign one or more authorities that returning from getAuthority method like "USER""ADMIN"
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));//Collectors.joining(",") == join the authorities one by one coma separately.
		claims.put("authorities", authorities);
		log.info("[generateToken] "+"Authorities assigning End..");

		String token = createToken(claims, username);
		log.info("[generateToken] "+"Token creation end..");

		return token;
	}

	private String createToken(Map<String, Object> claims, String subject) {
		log.info("[createToken] "+"Token creation started..");

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		log.info("[validateToken] "+"Token validation started..");
		SecurityDto securityDto = extractClaim(token);
		boolean isTokExpired = securityDto.getExpDate().before(new Date());

		if (userDetails.getUsername().equals(securityDto.getUserName()) && !isTokExpired){
			log.info("[validateToken] "+"Token validation success..");
			return true;
		}
		log.info("[validateToken] "+"Token validation fail..");
		return false;
	}

}
