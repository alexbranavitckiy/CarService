package com.netcracker.security;

import com.netcracker.security.jwt.ExceptionHandlerFilter;
import com.netcracker.security.jwt.JWTRequestFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
 private ExceptionHandlerFilter exceptionHandlerFilter;

 @Autowired
 public void setMyUserDetailsService(ExceptionHandlerFilter exceptionHandlerFilter, JWTRequestFilter filter, MyUserDetailsService myUserDetailsService) {
  this.myUserDetailsService = myUserDetailsService;
  this.exceptionHandlerFilter = exceptionHandlerFilter;
  this.filter = filter;
 }

 @Override
 protected void configure(HttpSecurity http) throws Exception {
  http
   .csrf()
   .disable()
   .authorizeRequests()
   .antMatchers("/person/**").hasAnyAuthority("REGISTERED","RECEPTIONIST")
   .antMatchers("/aut/**").hasAnyAuthority("MASTER","RECEPTIONIST")
   .antMatchers("/details/**").hasAnyAuthority("RECEPTIONIST")
   .antMatchers("/registration/**", "/**", "/swagger-ui/**").permitAll()
   .anyRequest().authenticated()
   .and().userDetailsService(myUserDetailsService)
   .exceptionHandling()
   .authenticationEntryPoint(
    (request, response, authException) ->
     response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
   .and()
   .formLogin()
   .loginPage("/swagger-ui/").failureUrl("/swagger-ui/")
   .defaultSuccessUrl("/swagger-ui/")
   .and().logout().logoutUrl("/person/logout").logoutSuccessUrl("/");
  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
  http.addFilterBefore(exceptionHandlerFilter, JWTRequestFilter.class);

 }

 @Bean
 PasswordEncoder getPasswordEncoder() {
  return new BCryptPasswordEncoder();
 }

 @Bean
 @Override
 public AuthenticationManager authenticationManagerBean() throws Exception {
  return super.authenticationManagerBean();
 }


}