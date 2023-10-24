package com.princess.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		
		security.userDetailsService(securityUserDetailsService);
		
		security.authorizeRequests().antMatchers("/", "/system/**").permitAll()
									.antMatchers("/board/**").authenticated()
									.antMatchers("/product/**").authenticated()
									.antMatchers("/mypage/**").authenticated()
									.antMatchers("/thunder/**").hasAnyRole("ADMIN", "THUNDER")
									.antMatchers("/admin/**").hasRole("ADMIN");
		
		security.csrf().disable();
		security.formLogin().loginPage("/system/login").defaultSuccessUrl("/product/getProductList", true);
		security.exceptionHandling().accessDeniedPage("/system/accessDenied");
		security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
		
		return security.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
