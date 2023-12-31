package com.itechgenie.apps.jdk11.sbfilter.security;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomNonReactiveUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		log.debug("Inside CustomReactiveUserDetailsService.findByUsername: user id: " + username);

		CustomUserDetails ud = new CustomUserDetails();
		ud.setUsername(username);
		ud.setSessionId("ITG".concat(UUID.randomUUID().toString()));
		ud.setClientId("ITG-WEB");

		return ud;
	}

}
