package com.netcracker.security;

import com.netcracker.services.ClientServices;
import com.netcracker.user.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegister {

 private final PasswordEncoder passwordEncoder;
 private final ClientServices clientServices;

 @Autowired
 public UserRegister(PasswordEncoder passwordEncoder, ClientServices clientServices) {
  this.passwordEncoder = passwordEncoder;
  this.clientServices = clientServices;
 }

 public void registerNewUser(Clients clients) {
  if (clientServices.getClientsByName(clients.getName()).isEmpty()) {
   clients.setPassword(passwordEncoder.encode(clients.getPassword()));
   clientServices.addObjectInClient(clients);
  }
 }

}
