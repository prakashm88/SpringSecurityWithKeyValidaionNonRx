package com.itechgenie.apps.jdk11.sbfilter.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.itechgenie.apps.jdk11.sbfilter.providers.CustomAuthenticationProvider;
import com.itechgenie.apps.jdk11.sbfilter.security.CustomNonReactiveAuthenticationManager;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppSecurityConfig {

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Autowired
	CustomNonReactiveAuthenticationManager customReactiveAuthenticationManager;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				requests -> requests.requestMatchers("/", "/public/**", "/**login**", "/error", "/webjars/**",
						"/actuator/**", "/v3/api-docs/**", "swagger-ui.html").permitAll().anyRequest().authenticated())
				.oauth2ResourceServer((oauth2ResourceServer) -> {
					oauth2ResourceServer.authenticationManagerResolver(resolver());
				})

				.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	AuthenticationManagerResolver<HttpServletRequest> resolver() {
		return request -> {
			if (request.getRequestURI().startsWith("/")) {
				return customReactiveAuthenticationManager;
			}
			// Return a different authentication manager if needed
			return null;
		};
	}
}
