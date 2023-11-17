package com.ang.rental.jwtfilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ang.rental.jwtutill.JwtUtil;
import com.ang.rental.services.GroupUserDetailservice;


@Component
public class JwtFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private GroupUserDetailservice groupUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			userName = jwtUtil.extractUsername(token);
		}

		if (userName != null && authorizationHeader.startsWith("Bearer ")) {

			UserDetails userDetails = groupUserDetailService.loadUserByUsername(userName);

			if (jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				log.warn("Authentication failure!!");
				response.setStatus(401);
			}
		}

		filterChain.doFilter(request, response);
	}
}
