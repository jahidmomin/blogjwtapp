package com.springboot.blog.app.jwt.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.blog.app.jwt.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		request se header nikalo
		final String header = request.getHeader("Authorization");
		String jwtToken = null;
		String userName = null;

		if (header != null && header.startsWith("Bearer ")) {
//			bearer + space =7
			jwtToken = header.substring(7);
			try {
//				step 5 to create jwt util class
//				verification n all
				userName = jwtUtil.getUserNameFromToken(jwtToken);

			} catch (ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("Jwt Token is Expired");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				System.out.println("Unable to get Jwt Token");
			}
		} else {
			System.out.println("Jwt Token Does Not Start With Bearer");
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtService.loadUserByUsername(userName);
			if (jwtUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		
//		add filter cvhain line
		filterChain.doFilter(request, response);
	}

}
