package com.gft.veterinariagft.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gft.veterinariagft.services.UsuarioService;

@Configuration @Profile("dev")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class webSecurityConfigTest {
	
	@Autowired
	private UsuarioService usuarioService;

	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	 AuthenticationManager getAuthenticationManager(HttpSecurity http)throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(usuarioService).passwordEncoder(getPasswordEncoder());
		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
		return authenticationManager;
	}

	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
		.anyRequest()
			.permitAll()		
		.and().csrf().disable();

		return http.getOrBuild();
	}
	
	
}
