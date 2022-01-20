package com.springboot.blog.app.jwt.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.blog.app.service.JwtService;

//Step 3
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthenticationEntryPoint jwtauthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtrequestfilter;
	
//	yaha interface kre jwtservice class ka
	@Autowired
	private JwtService jwtService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors();
		httpSecurity.csrf().disable().authorizeRequests()
		.antMatchers("/swagger-ui.html/**").permitAll()
		.antMatchers("/authenticate","/registerNewUser").permitAll()
		.antMatchers(HttpHeaders.ALLOW).permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtauthenticationEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.addFilterBefore(jwtrequestfilter,UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		step 4 to create jwt service
		authenticationManagerBuilder.userDetailsService(jwtService)
		.passwordEncoder(encoder());
	}
	
	
	
	
/**	
	
	package com.mg.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()

				.antMatchers("/api/v1/user/patient/accept/reschedule/**").hasRole("patient")
				.antMatchers("/api/v1/deviceparam/get/all/deviceparameters/**").hasRole("patient")
				.antMatchers("/api/v1/consultation/get/configurations/**").hasRole("patient")
				.antMatchers("/api/v1/consultation/get/test/result/**").hasRole("patient")
				.antMatchers("/api/v1/consultation/send/testdata/**").hasRole("patient")
				.antMatchers("/api/v1/appointment/booking/list/appointment/**").hasRole("patient")
				.antMatchers("/api/v1/appointment/payment/summary/**").hasRole("patient")
				.antMatchers("/api/v1/appointment/slot/locking/**").hasRole("patient")

				.antMatchers("/api/v1/audiologist/**").hasRole("audiologist")
				.antMatchers("/api/v1/appointment/accept/**").hasRole("audiologist")

				.antMatchers("/api/v1/jwt/login").permitAll().antMatchers("/api/v1/auth/**").permitAll()

				.antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources",
						"/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**")
				.permitAll().antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(this.unauthorizedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	AuthenticationManager authentication() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}**/

}
