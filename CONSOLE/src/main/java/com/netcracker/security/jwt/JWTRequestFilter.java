package com.netcracker.security.jwt;

import com.netcracker.security.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component//OncePerRequest Filterguarantees that the filter will be called 1 time
public class JWTRequestFilter extends OncePerRequestFilter {

 private final MyUserDetailsService myUserDetailsService;
 private final JWTUtil jwtUtil;

 public JWTRequestFilter(MyUserDetailsService myUserDetailsService, JWTUtil jwtUtil) {
  this.myUserDetailsService = myUserDetailsService;
  this.jwtUtil = jwtUtil;
 }

 @Override
 protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                 FilterChain filterChain) throws ServletException, IOException {
  String token = null;
  String username = null;
  Cookie[] cookies = httpServletRequest.getCookies();
  String tokenHeader = httpServletRequest.getHeader("token");
  log.info(tokenHeader);
  if (cookies != null) {
   for (Cookie cookie : cookies) {
    if (cookie.getName().equals("token")) {
     token = cookie.getValue();
     username = jwtUtil.extractUsername(token);
    }
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
     UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
     if (jwtUtil.validateToken(token, userDetails)) {
      UsernamePasswordAuthenticationToken authenticationToken =
       new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
     }
    }
   }
  }
  filterChain.doFilter(httpServletRequest, httpServletResponse);
 }
}
