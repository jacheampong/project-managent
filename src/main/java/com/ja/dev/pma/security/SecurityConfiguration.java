package com.ja.dev.pma.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	
	/**
	 * authentication - who 
	 * using in memory authentication 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.withDefaultSchema()
			.withUser("myuser")
				.password("pass1")
				.roles("USER")
			.and()
			.withUser("jon")
				.password("pass2")
				.roles("USER")
			.and()
			.withUser("manager")
				.password("pass3")
				.roles("ADMIN");
				
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	/**
	 * authorization - what 
	 * Using roles to authenticate users
	 * Only ADMIN can access new pages 
	 * Other authenticated user cane access all page but /new
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/project/new").hasRole("ADMIN")
			.antMatchers("/employee/new").hasRole("ADMIN")
			.antMatchers("/h2_console/**").permitAll()
			.antMatchers("/").authenticated().and().formLogin();
		
		// disabled in order to be able to access H2 DB url
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

}
