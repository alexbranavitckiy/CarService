package com.netcracker.security;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Slf4j
@Hidden
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

 private MyUserDetailsService myUserDetailsService;

 @Autowired
 public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
  this.myUserDetailsService = myUserDetailsService;
 }

 @Override
 protected void configure(HttpSecurity http) throws Exception {
  http.csrf().disable().authorizeRequests()
   .antMatchers("/clients/**").authenticated()
   .antMatchers("/aut/master/**").hasRole("REGISTERED")
   .antMatchers("/aut/masterReceiver/**").hasRole("UNREGISTERED")
   .and()
   .formLogin()
   .loginPage("/login")
   .loginProcessingUrl("/perform_login")
   .defaultSuccessUrl("/index", true)
   .failureUrl("/login?error=true")
   .and()
   .logout()
   .logoutUrl("/perform_logout")
   .logoutSuccessUrl("/index");//после выхода
 }

 @Bean
 PasswordEncoder getPasswordEncoder() {
  return new BCryptPasswordEncoder();
 }

 @Bean
 public DaoAuthenticationProvider daoAuthenticationProvider() {
  DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
  daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
  daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
  return daoAuthenticationProvider;
 }

}