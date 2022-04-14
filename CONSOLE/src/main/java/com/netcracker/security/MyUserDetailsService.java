package com.netcracker.security;

import com.netcracker.EnumRole;
import com.netcracker.services.ClientServices;
import com.netcracker.services.MasterReceiverServices;
import com.netcracker.services.MasterServices;
import com.netcracker.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

 private final ClientServices clientServices;
 private final MasterReceiverServices masterReceiverServices;
 private final MasterServices masterServices;

 @Autowired
 private MyUserDetailsService(MasterReceiverServices masterReceiverServices, MasterServices masterServices, ClientServices clientServices) {
  this.clientServices = clientServices;
  this.masterServices = masterServices;
  this.masterReceiverServices = masterReceiverServices;
 }

 @Override
 public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
  Map<String, Object> user = clientServices.getRoleClientByLogin(s);
  if (user != null && user.get("role") != null && user.get("login") != null && user.get("password") != null) {
   return new User(user.get("login").toString(), user.get("password").toString(), grantedAuthorities(List.of(user.get("role").toString())));
  }
  throw new UsernameNotFoundException("user not found doh!");
 }


 private Collection<? extends GrantedAuthority> grantedAuthorities(Collection<String> roles) {
  return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
 }


}
