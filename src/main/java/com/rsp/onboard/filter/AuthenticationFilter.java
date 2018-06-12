package com.rsp.onboard.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class AuthenticationFilter extends GenericFilterBean {
	
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);


	@Value("${app.auth.token}")
	private String authorizationToken;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterchain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		String xAuth = httpRequest.getHeader("X-Authorization");

		String pathInfo = httpRequest.getRequestURI();
		
		logger.debug("incoming request: {0}", pathInfo);

		if (pathInfo != null && pathInfo.startsWith("/api") && !authorizationToken.equals(xAuth)) {
			throw new SecurityException("Invalid Authorization Token");
		}

		filterchain.doFilter(servletRequest, servletResponse);

	}

}
