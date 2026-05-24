package com.example.employeeservice.config;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestId = UUID.randomUUID().toString();

		log.info("[REQ-ID:{}] {} {}", requestId, request.getMethod(), request.getRequestURI());

		filterChain.doFilter(request, response);

		log.info("[REQ-ID:{}] RESPONSE STATUS: {}", requestId, response.getStatus());
	}
}