package com.netcracker.security;

import com.netcracker.services.ClientServices;
import com.netcracker.services.impl.ClientServicesImpl;
import com.netcracker.user.Clients;
import com.netcracker.user.Role;
import com.netcracker.user.RoleUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

 private ClientServices clientServices;

 @Autowired
 private MyUserDetailsService(ClientServices clientServices) {
  this.clientServices = clientServices;
 }

 @Override
 public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
  Optional<Clients> user = clientServices.getClientsByName(s);
  if (user.isPresent())
   return new User(user.get().getName(), user.get().getPassword(), grantedAuthorities(List.of(user.get().getRoleUser())));
  else
   throw new UsernameNotFoundException("user not found doh!");
 }

 private Collection<? extends GrantedAuthority> grantedAuthorities(Collection<RoleUser> roles) {
  return roles.stream().map(r -> new SimpleGrantedAuthority(r.getCode())).collect(Collectors.toList());

 }


}
