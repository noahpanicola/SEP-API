package com.mydevgeek.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	private static final String ADMIN = "Landlord";
	private static final String USER = "Tenant";
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
		.csrf().disable()
			.exceptionHandling()
        		.authenticationEntryPoint(restAuthenticationEntryPoint)
        	.and()
			.authorizeRequests()
			.antMatchers("/", "/auth/login").permitAll()
			.anyRequest().authenticated()
		.and()
			.cors()
		.and()
			.formLogin()
			.usernameParameter("email").passwordParameter("password")
			.loginProcessingUrl("/auth/login").successHandler(new CustomAuthenticationSuccessHandler())
		.and()
			.logout();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
		.withUser("noahp").password("test").roles(USER, ADMIN);
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT email, password, is_active from user WHERE email=?")
		.authoritiesByUsernameQuery(
				"SELECT u.email, r.name from user u " +
				"JOIN role r ON u.role_id = r.role_id " +
				"WHERE u.email=?"
		).rolePrefix("ROLE_");
	}

}
