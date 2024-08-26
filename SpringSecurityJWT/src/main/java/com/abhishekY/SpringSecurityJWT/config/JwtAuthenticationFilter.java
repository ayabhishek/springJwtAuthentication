package com.abhishekY.SpringSecurityJWT.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor //created constructor using any final field we declare in this class
public class JwtAuthenticationFilter extends OncePerRequestFilter{// you can also implement the Filter class
	//but the Filter class is implemented within the Parent of OPRF, so both do same thing

	private final JwtService jwtService;
	
	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response, 
			@NonNull FilterChain filterChain //chain of responses and their respective filtered request(using a chained patter)
			)throws ServletException, IOException {
		
		final String authHeader=request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if(authHeader==null || authHeader.startsWith("Bearer ")) {//here the authHeader compulsorily starts with "Bearer"
			filterChain.doFilter(request, response); //do filtering for next req and res
			return;
		}
		//extraction to validate from db
		
		//extract the jwt token
		jwt=authHeader.substring(7); // assign after 7th index because first 7 indexes are for "Bearer"+" "
		//extract user email
		userEmail= jwtService.extractUsername(jwt);//to extract the userEmail from JWT token we will need a class to manipulate the jwt token
	}

}
