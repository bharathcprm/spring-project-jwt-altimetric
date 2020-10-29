package com.spring.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.jwt.config.JwtTokenUtil;
import com.spring.jwt.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String userName = null;
		String jwtToken = null;

		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);
			try {
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);

			} catch (IllegalArgumentException e) {
				logger.error("Unnable to get Jwt token");
			} catch (ExpiredJwtException e) {
				logger.error("Jwt token expired");
			}

		} else {
			logger.warn("Jwt Token does not start with Bearer string");
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {

			UserDetails userDetails = userService.loadUserByUsername(userName);

			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				logger.info("Invalid token");
			}
			
		}
		filterChain.doFilter(request, response);
	}

}
