package com.princess.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
         .antMatchers("/thunder/standByList").hasAuthority("MEMBER")
         .antMatchers("/thunder/**").hasAuthority("THUNDER")
         .antMatchers("/admin/**").hasAuthority("ADMIN");
      http.sessionManagement()
	      .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)  // 세션 생성 전략
	      .invalidSessionUrl("/session-invalid")                     // 유효하지 않은 세션 시 리다이렉트 URL
	      .maximumSessions(1)                                        // 최대 허용 가능 세션 수, 1로 설정하면 동시 세션 접속 제한
	      .expiredUrl("/session-expired");  
      return http.build();
   }
   @Bean
   public PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }
   


}
