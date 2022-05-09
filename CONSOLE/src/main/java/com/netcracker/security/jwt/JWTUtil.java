package com.netcracker.security.jwt;

import com.netcracker.DTO.response.ContactConfirmationPayload;
import io.jsonwebtoken.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JWTUtil {

 @Value("${auth.secret}")
 private String secret;

 private String createToken(Map<String, Object> claims, String username) {
  return Jwts
   .builder()
   .setClaims(claims)
   .setSubject(username)
   .setIssuedAt(new Date(System.currentTimeMillis()))
   .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 10))
   .signWith(SignatureAlgorithm.HS256, secret).compact();
 }

 public String generateToken(UserDetails userDetails) {
  Map<String, Object> claims = new HashMap<>();
  return createToken(claims, userDetails.getUsername());
 }

 public String generateToken(ContactConfirmationPayload userDetails) {
  Map<String, Object> claims = new HashMap<>();
  return createToken(claims, userDetails.getLogin());
 }

 public String generateToken(String userDetails) {
  Map<String, Object> claims = new HashMap<>();
  return createToken(claims, userDetails);
 }

 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
  Claims claims = extractAllClaims(token);
  return claimsResolver.apply(claims);
 }

 private Claims extractAllClaims(String token) {
  return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
 }

 public String extractUsername(String token) {
  return extractClaim(token, Claims::getSubject);
 }

 public Date extractExpiration(String token) {
  return extractClaim(token, Claims::getExpiration);
 }

 public Boolean isTokenExpired(String token) {
  return extractExpiration(token).before(new Date());
 }

 public Boolean validateToken(String token, UserDetails userDetails) {
  String username = extractUsername(token);
  return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
 }

 public boolean validateToken(@NonNull String token) {
  try {
   Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
   return true;
  } catch (ExpiredJwtException expEx) {
   log.error("Token expired", expEx);
   throw expEx;
  } catch (UnsupportedJwtException unsEx) {
   log.error("Unsupported jwt", unsEx);
  } catch (MalformedJwtException mjEx) {
   log.error("Malformed jwt", mjEx);
  } catch (Exception e) {
   log.error("invalid token", e);
  }
  return false;
 }


}
