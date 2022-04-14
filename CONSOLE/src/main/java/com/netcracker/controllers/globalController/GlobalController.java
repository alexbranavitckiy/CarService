package com.netcracker.controllers.globalController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@Slf4j
@ControllerAdvice
public class GlobalController {

 @ModelAttribute("searchRole")
 public String searchWordDto(Authentication authentication) {
  if (authentication != null) {
   return String.valueOf(authentication.getAuthorities());
  } else
   return "NO";
 }


}
