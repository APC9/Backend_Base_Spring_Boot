package com.example.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.user.services.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WevSecurityConfig {

  @Autowired
  private UserAuth userAuth;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
      .csrf(csrf->csrf.disable())
      .authorizeHttpRequests( ( authz) -> authz
        .requestMatchers( HttpMethod.DELETE,"/api/v1/users/**").hasRole("admin")
        .requestMatchers( HttpMethod.PUT,"/api/v1/users/**").hasRole("admin")
        //.requestMatchers( HttpMethod.GET, "api/v1/users/filter/{name:^[0-9]}").authenticated() // si name = number: Access Denied
        .anyRequest().permitAll() 
      );
      return http.build();
  }

  @Bean 
  public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder, UserServiceImpl userService )throws Exception {
    
    return httpSecurity.getSharedObject( AuthenticationManagerBuilder.class)
      .authenticationProvider(this.userAuth).build();
  } 
}
