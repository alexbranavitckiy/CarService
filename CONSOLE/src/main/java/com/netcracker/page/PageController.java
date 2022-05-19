package com.netcracker.page;

import com.netcracker.DTO.car.MarkDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.page.Paged;
import com.netcracker.car.Mark;
import com.netcracker.services.MarkServices;
import com.netcracker.user.Client;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class PageController {

 private final MarkServices markServices;

 @Autowired
 PageController(MarkServices markServices) {
  this.markServices = markServices;
 }

 @ModelAttribute("pathVariable")
 public String pathVariable() {
  return "NO";
 }

 @GetMapping(value = {"/login"})
 public String login() {
  return "login";
 }

 @ModelAttribute("clients")
 public Client searchWordDto() {
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
 public String cars(Model model) {
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

 @ModelAttribute("markDto")
 public List<MarkDto> getTagListDesc() throws SaveSearchErrorException {
  return this.markServices.getAllMark(0, 9);
 }

 @ModelAttribute("posts")
 public Paged<Mark> posts() {
  return markServices.getPage(1, 6);
 }

 @ApiOperation("Get all car brands")
 @GetMapping("/page/")
 public String posts(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                     @RequestParam(value = "size", required = false, defaultValue = "6") int size, Model model) {
  model.addAttribute("posts", markServices.getPage(pageNumber, size));
  return "index";
 }


}

