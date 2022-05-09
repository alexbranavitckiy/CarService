package com.netcracker.security.jwt;

import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.security.MyUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
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
@Component
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
  String authHeader = httpServletRequest.getHeader("Authorization");
  if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer")) {
   String jwt = authHeader.substring(7);
   try {
    jwtUtil.validateToken(jwt);
   } catch (Exception e) {
    httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
    return;
   }
   if (jwt.isBlank()) {
    httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
   } else {
    try {
     String login = jwtUtil.extractUsername(jwt);
     if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = myUserDetailsService.loadUserByUsername(login);
      if (jwtUtil.validateToken(jwt, userDetails)) {
       UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
         userDetails, null, userDetails.getAuthorities());
       authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
       SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
     }
    } catch (Exception exc) {
     httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
    }
   }
  }
  filterChain.doFilter(httpServletRequest, httpServletResponse);
 }

}
