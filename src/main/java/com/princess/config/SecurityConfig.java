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
      http.formLogin()
      .loginPage("/system/login")
      .defaultSuccessUrl("/product/getProductList?type=prod", true);
      http.exceptionHandling().accessDeniedPage("/system/accessDenied");   
      
      http
      .logout()
      .logoutUrl("/system/logout") // 로그아웃 URL
      .logoutSuccessUrl("/system/login") // 로그아웃 성공 후 리디렉션할 URL
      .invalidateHttpSession(true) // 세션 무효화
      .deleteCookies("JSESSIONID"); // 세션 쿠키 삭제
     
      
      http.authorizeHttpRequests()
         .antMatchers("/","/system/**").permitAll()
         .antMatchers("/board/**").authenticated()
         .antMatchers("/product/**").authenticated()
         .antMatchers("/mypage/**").authenticated()
         .antMatchers("/admin/**").hasAuthority("ADMIN")
         .antMatchers("/thunder/**").hasAnyAuthority("THUNDER","ADMIN");
      

      return http.build();
   }
   @Bean
   public PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }
  
}
