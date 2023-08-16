package com.itechgenie.apps.jdk11.sbfilter.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/secure")
public class SBAppController {

	@GetMapping(value = "/api/user")
	public String health() throws Exception {
		log.debug("Inside secure controller");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		log.debug("Logged in user info: " + auth.getName());

		String userName = auth.getName();

		return "LoggedInUser".concat(": ").concat(userName);
	}
	
	@GetMapping("/api/user/details")
	public ResponseEntity<Authentication> userDetails() {
		log.debug("Inside secure controller.userDetails");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		HttpStatus status = HttpStatus.OK;

		return ResponseEntity.status(status).body(auth);
	}
}
