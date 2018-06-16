package com.rsp.onboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.rsp.onboard.filter.HeaderAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${app.auth.token}")
	private String authorizationToken;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin().disable();
		http.antMatcher("/api/**")
			.addFilterBefore(new HeaderAuthorizationFilter(authorizationToken), BasicAuthenticationFilter.class);

	}

}
