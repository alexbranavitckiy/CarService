package com.netcracker.controllers.page;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.clients.ClientDto;
import com.netcracker.car.CarClient;
import com.netcracker.security.UserRegister;
import com.netcracker.services.CarServices;
import com.netcracker.services.ClientServices;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@ApiIgnore
@Controller
public class PageController {

 private final ClientServices clientServices;
 private final CarServices carServices;
 private final UserRegister userRegister;

 @Autowired
 PageController(UserRegister userRegister, CarServices carServices, ClientServices clientServices) {
  this.carServices = carServices;
  this.userRegister = userRegister;
  this.clientServices = clientServices;
 }

 @ModelAttribute("pathVariable")
 public String pathVariable() {
  return "NO";
 }

 @GetMapping(value = {"/login"})
 public String login() {
  return "login";
 }

 @ModelAttribute("client")
 public Client searchWordDto(Model model, Principal principal) {
  if (principal != null) {
   Optional<ClientDto> client = clientServices.getClientsByName(principal.getName());
   client.ifPresent(clientDto -> model.addAttribute("client", clientDto));
  }
  return new Client();
 }

 @GetMapping(value = {"/", "/index"})
 public String index() {
  return "index";
 }

 @GetMapping(value = {"/index/profile"})
 public String profile(Model model) {
  model.addAttribute("pathVariable", "profile");
  return "index";
 }

 @GetMapping(value = {"/index/cars"})
 public String cars(Model model, Principal principal) {
  model.addAttribute("pathVariable", "cars");
  if (principal != null) {
   List<CarClientDto> carClientList = carServices.getCarByLoginClient(principal.getName());
   model.addAttribute("car", carClientList);
   if (carClientList != null) {
    model.addAttribute("size", carClientList.size());
   } else model.addAttribute("size", 0);
  }
  return "index";
 }

 @GetMapping(value = {"/index/myRequest"})
 public String myRequest(Model model) {
  model.addAttribute("pathVariable", "myRequest");
  return "index";
 }

 @GetMapping(value = {"/index/addCar"})
 public String addCar(Model model) {
  model.addAttribute("pathVariable", "addCar");
  return "index";
 }

 @GetMapping(value = {"/index/atWork"})
 public String atWork(Model model) {
  model.addAttribute("pathVariable", "atWork");
  return "index";
 }

 @GetMapping(value = {"/index/myEntries"})
 public String myEntries(Model model) {
  model.addAttribute("pathVariable", "myEntries");
  return "index";
 }

}




