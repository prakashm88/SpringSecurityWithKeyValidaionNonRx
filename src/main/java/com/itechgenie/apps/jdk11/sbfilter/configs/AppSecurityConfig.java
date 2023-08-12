package com.itechgenie.apps.jdk11.sbfilter.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import com.itechgenie.apps.jdk11.sbfilter.providers.CustomAuthenticationProvider;

@Configuration
public class AppSecurityConfig {
	@Autowired
	private CustomAuthenticationProvider authProvider;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkUri;
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authProvider);
		return authenticationManagerBuilder.build();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests
				.requestMatchers("/", "/public/**", "/**login**", "/error", "/webjars/**", "/actuator/**", "/v3/api-docs/**", "swagger-ui.html").permitAll()
				.anyRequest().authenticated())

				.oauth2ResourceServer(
						(oauth2ResourceServer) ->  oauth2ResourceServer.jwt((jwt) -> jwt.decoder(jwtDecoder())))
		// .oauth2Login(Customizer.withDefaults()) ;

		 .httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri(jwkUri)
			//	.withIssuerLocation(issuerUri) 
				.build();
	}
}
