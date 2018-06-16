package com.rsp.onboard.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

public class HeaderAuthorizationFilter extends OncePerRequestFilter {

	private String authorizationToken;

	private static final Logger logger = LoggerFactory.getLogger(HeaderAuthorizationFilter.class);

	public HeaderAuthorizationFilter(String authorizationToken) {
		this.authorizationToken = authorizationToken;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			FilterChain filterchain) throws ServletException, IOException {
		String xAuth = servletRequest.getHeader("X-Authorization");

		String pathInfo = servletRequest.getRequestURI();

		logger.debug("incoming request: {0}", pathInfo);

		if (!authorizationToken.equals(xAuth)) {
			servletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid Authorization Token.");
		}

		filterchain.doFilter(servletRequest, servletResponse);

	}

}
