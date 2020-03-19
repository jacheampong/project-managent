package com.ja.dev.pma.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.usersByUsernameQuery("select username, password, enabled "
				+ "from user_accounts where username = ?")
		.authoritiesByUsernameQuery("select username, role from "
				+ "user_accounts where username = ?")
		.dataSource(dataSource)
		.passwordEncoder(bCryptEncoder);
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
			.antMatchers("/project/save").hasRole("ADMIN")
			.antMatchers("/employee/new").hasRole("ADMIN")
			.antMatchers("/employee/save").hasRole("ADMIN")
			.antMatchers("/", "/**").permitAll()
			.and()
			.formLogin();
//		.formLogin().loginPage("login-page"); custom login page

	}

}
