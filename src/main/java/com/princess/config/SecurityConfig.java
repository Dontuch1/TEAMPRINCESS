package com.princess.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   @Autowired
   private PrincessUserDetailsService userDetailsService;

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.userDetailsService(userDetailsService);
      http.csrf().disable();
      http.formLogin().loginPage("/system/login").defaultSuccessUrl("/product/getProductList", true);
      http.logout().logoutUrl("/system/logout");
      http.exceptionHandling().accessDeniedPage("/system/accessDenied");   
      
      http.authorizeHttpRequests()
         .antMatchers("/","/system/**").permitAll()
         .antMatchers("/board/**").authenticated()
         .antMatchers("/product/**").authenticated()
         .antMatchers("/mypage/**").authenticated()
         .antMatchers("/admin/**").hasAuthority("ADMIN")
         .antMatchers("/thunder/**").hasAnyAuthority("ADMIN","THUNDER");
      
      return http.build();
   }
   @Bean
   public PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }
   


}
