package com.netcracker.security;

import com.netcracker.EnumRole;
import com.netcracker.repository.ClientsRepository;
import com.netcracker.repository.MasterReceiverRepository;
import com.netcracker.repository.MasterRepository;
import com.netcracker.services.ClientServices;
import com.netcracker.services.impl.ClientServicesImpl;
import com.netcracker.user.*;
import io.swagger.v3.oas.annotations.Hidden;
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
@Hidden
@Service
public class MyUserDetailsService implements UserDetailsService {

 private final ClientsRepository clientsRepository;
 private final MasterReceiverRepository masterReceiverRepository;
 private final MasterRepository masterRepository;

 @Autowired
 private MyUserDetailsService(MasterRepository masterRepository, MasterReceiverRepository masterReceiverRepository, ClientsRepository clientsRepository) {
  this.clientsRepository = clientsRepository;
  this.masterRepository = masterRepository;
  this.masterReceiverRepository = masterReceiverRepository;
 }

 @Override
 public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
  Optional<Clients> user = clientsRepository.getAllByLogin(s);
  if (user.isPresent()) {
   return new User(user.get().getName(), user.get().getPassword(), grantedAuthorities(List.of(user.get().getRoleUser())));
  }
  Optional<MasterReceiver> masterReceiver = masterReceiverRepository.getAllByLogin(s);
  if (masterReceiver.isPresent()) {
   return new User(masterReceiver.get().getName(), masterReceiver.get().getPassword(), grantedAuthorities(List.of(masterReceiver.get().getRole())));
  }
  Optional<Master> master = masterRepository.getAllByLogin(s);
  if (master.isPresent()) {
   return new User(master.get().getName(), master.get().getPassword(), grantedAuthorities(List.of(master.get().getRole())));
  }
  throw new UsernameNotFoundException("user not found doh!");
 }


 private Collection<? extends GrantedAuthority> grantedAuthorities(Collection<EnumRole> roles) {
  return roles.stream().map(r -> new SimpleGrantedAuthority(r.getCode())).collect(Collectors.toList());
 }


}
