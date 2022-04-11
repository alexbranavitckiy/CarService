package com.netcracker.controllers;

import com.netcracker.user.Clients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class PageController {

 @ModelAttribute("pathVariable")
 public String pathVariable() {
  return "NO";
 }
 @GetMapping(value = {"/login"})
 public String login() {
  return "login";
 }

 @ModelAttribute("clients")
 public Clients searchWordDto() {
  return new Clients();
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
 public String cars( Model model) {
  model.addAttribute("pathVariable", "cars");
  return "index";
 }

 @GetMapping(value = {"/index/myRequest"})
 public String myRequest(Model model) {
  model.addAttribute("pathVariable", "myRequest");
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




