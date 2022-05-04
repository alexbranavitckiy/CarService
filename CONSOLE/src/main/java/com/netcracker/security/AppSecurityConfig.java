package com.netcracker.security;

import com.netcracker.security.jwt.JWTRequestFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
@Configuration(value = "AppSecurityConfig")
@EnableWebSecurity
@EnableGlobalMethodSecurity(
 prePostEnabled = true,
 securedEnabled = true,
 jsr250Enabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

 private MyUserDetailsService myUserDetailsService;
 private JWTRequestFilter filter;

 @Autowired
 public void setMyUserDetailsService(JWTRequestFilter filter, MyUserDetailsService myUserDetailsService) {
  this.myUserDetailsService = myUserDetailsService;
  this.filter = filter;
 }

 @Override
 protected void configure(HttpSecurity http) throws Exception {
  http
   .csrf()
   .disable()
   .authorizeRequests()
   .antMatchers("/swagger-ui/**", "/person/**").hasAnyAuthority("REGISTERED","MASTER","RECEPTIONIST")
   .antMatchers("/aut/**").hasAnyAuthority("MASTER","REGISTERED", "RECEPTIONIST")
   .antMatchers("/details/**").hasAnyAuthority("REGISTERED","MASTER","RECEPTIONIST")
   .antMatchers("/**"
   ).permitAll()
   .anyRequest().authenticated()
   .and()
   .formLogin()
   .loginPage("/login").failureUrl("/login")
   .defaultSuccessUrl("/swagger-ui/")
   .and().logout().logoutUrl("/person/logout").logoutSuccessUrl("/").deleteCookies("token");
  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
 }


 @Bean
 PasswordEncoder getPasswordEncoder() {
  return new BCryptPasswordEncoder();
 }

 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth
   .userDetailsService(myUserDetailsService)
   .passwordEncoder(getPasswordEncoder());
 }

 @Bean
 @Override
 public AuthenticationManager authenticationManagerBean() throws Exception {
  return super.authenticationManagerBean();
 }


}