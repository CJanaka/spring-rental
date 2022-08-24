package com.ang.rental.security.configuration;

import com.ang.rental.jwtfilter.JwtFilter;
import com.ang.rental.services.GroupUserDetailservice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPattern;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private GroupUserDetailservice GroupUserDetailService;

	@Autowired
	private JwtFilter jwtFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(GroupUserDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
        http.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                return new CorsConfiguration().applyPermitDefaultValues();
            }
        });
        
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/api/v1/add").permitAll().antMatchers("/api/v1/listbyname/**").permitAll()
				.antMatchers("/api/v1/detailed-listing/**").permitAll()
				.antMatchers("/api/v1/list-and-mainimage").permitAll()
				.antMatchers("/api/v1/all-categories").permitAll()
				.antMatchers("/api/v1/all-listings/**").permitAll()
				.antMatchers("/api/v1/find/**").permitAll()
				.antMatchers("/api/v1/getlist/**").permitAll()
				.antMatchers("/api/v1/search/**").permitAll()
				.antMatchers("/api/v1/auth").permitAll()
				.antMatchers("/api/v1/listby-categoryid/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS,"/api/v1/**").permitAll()
				.and().authorizeRequests().antMatchers("/api/v1/**").authenticated().and().exceptionHandling().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
