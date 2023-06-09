package com.gft.veterinariagft.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gft.veterinariagft.filter.FiltroAutenticacao;
import com.gft.veterinariagft.services.AutenticacaoService;
import com.gft.veterinariagft.services.UsuarioService;

@Configuration @Profile(value = "default")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class webSecurityConfig {
	
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
		.antMatchers(HttpMethod.POST,"/v1/auth")
			.permitAll()
		.antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
			.permitAll()
		.antMatchers(HttpMethod.POST,"/v1/clientes")
		    .permitAll()
		.anyRequest().authenticated()
			.and().csrf().disable()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.addFilterBefore(new FiltroAutenticacao(new AutenticacaoService(), usuarioService), UsernamePasswordAuthenticationFilter.class);
		
	
		return http.getOrBuild();
	}
	
	
}
