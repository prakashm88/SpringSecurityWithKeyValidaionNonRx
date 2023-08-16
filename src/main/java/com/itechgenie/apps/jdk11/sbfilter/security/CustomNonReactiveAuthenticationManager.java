package com.itechgenie.apps.jdk11.sbfilter.security;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.itechgenie.apps.jdk11.sbfilter.utils.AppCommonUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomNonReactiveAuthenticationManager implements AuthenticationManager {

	@Autowired
	private CustomNonReactiveUserDetailsService userDetailsService;

	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	String jwkUrl;

	@Autowired
	HttpServletRequest request;

	@Override
	public Authentication authenticate(Authentication authentication) {
		log.debug("Inside CustomReactiveAuthenticationManager.authenticate: " + authentication);

		JwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkUrl).build();

		Jwt token = jwtDecoder.decode(authentication.getName());

		log.debug(
				"Inside CustomReactiveAuthenticationManager.authenticate: JWT Object: " + AppCommonUtil.toJson(token));

		CustomUserDetails ud = (CustomUserDetails) validateAndFetchUserDetails(token);

		/*
		 * HttpHeaders headers = ctx.get("headers");
		 * 
		 * MultiValueMap<String, Object> requestHeaders = getHttpHeaderToMap(headers);
		 * 
		 * log.debug("$$$ Headers from parent : " + AppCommonUtil.toJson(headers));
		 * log.debug("$$$ Headers from context : " +
		 * AppCommonUtil.toJson(requestHeaders));
		 */

		// Create CustomUserDetails object with request headers
		ud.setClientId("UPDATED-ITG");
		ud.setRequestHeaders(getAllHeaders());

		Authentication updatedAuthentication = new UsernamePasswordAuthenticationToken(ud, ud.getPassword(),
				ud.getAuthorities());

		log.debug("Inside CustomReactiveAuthenticationManager.updatedAuthentication: " + updatedAuthentication);

		return updatedAuthentication;

	}

	private UserDetails validateAndFetchUserDetails(Jwt token) {
		return userDetailsService.loadUserByUsername(token.getSubject());
	}

	public MultiValueMap<String, Object> getAllHeaders() {
		MultiValueMap<String, Object> headersMap = new LinkedMultiValueMap<>();
		Enumeration<String> headerNames = this.request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			Enumeration<String> headerValues = request.getHeaders(headerName);

			while (headerValues.hasMoreElements()) {
				String headerValue = headerValues.nextElement();
				headersMap.add(headerName, headerValue);
			}
		}

		return headersMap;
	}
}
