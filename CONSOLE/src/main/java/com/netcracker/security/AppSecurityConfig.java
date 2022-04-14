package com.netcracker.security;

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
  http.authorizeRequests()
   .antMatchers( "/swagger-ui/**", "/javainuse-openapi/**", "/swagger-resources/**",
    "/webjars/**","/clients/**","/index/**").hasAuthority("REGISTERED")
   .antMatchers("/aut/**").hasRole("MASTER")
   .antMatchers("/aut/details/**").hasAnyRole("MASTER", "RECEPTIONIST")
   .and()
   .formLogin()
   .loginPage("/login")
   .loginProcessingUrl("/perform_login")
   .defaultSuccessUrl("/index", false)
   .failureUrl("/login?error=true")
   .and()
   .logout()
   .logoutUrl("/perform_logout")
   .logoutSuccessUrl("/index")
   .deleteCookies("auth_code", "JSESSIONID")
   .invalidateHttpSession(true)
   .and().csrf().disable();
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