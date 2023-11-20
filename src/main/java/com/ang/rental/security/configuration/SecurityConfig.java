package com.ang.rental.security.configuration;

import com.ang.rental.Common;
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
		http.authorizeRequests().antMatchers("/add").permitAll().antMatchers("/api/v1/listbyname/**").permitAll()
				.antMatchers(Common.BASE_URL+"/detailed/listing/**").permitAll()
				.antMatchers(Common.BASE_URL+"/list/and/main/image").permitAll()
				.antMatchers(Common.BASE_URL+"/all/categories").permitAll()
				.antMatchers(Common.BASE_URL+"/all/listings/**").permitAll()
				.antMatchers(Common.BASE_URL+"/find/**").permitAll()
				.antMatchers(Common.BASE_URL+"/get/list/**").permitAll()
				.antMatchers(Common.BASE_URL+"/search/**").permitAll()
				.antMatchers(Common.BASE_URL+"/auth").permitAll()
				.antMatchers(Common.BASE_URL+"/update/user/**").permitAll()
				.antMatchers(Common.BASE_URL+"/update/listing/**").permitAll()
				.antMatchers(Common.BASE_URL+"/listby/categoryid/**").permitAll()
//				.antMatchers(HttpMethod.OPTIONS,"/api/v1/**").permitAll()
				.and().authorizeRequests().antMatchers(Common.BASE_URL+"/auth/**").authenticated().and().exceptionHandling().and()
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
