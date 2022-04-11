package com.netcracker.controllers.globalController;


import com.netcracker.DTO.errs.EmptySearchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlerController {

 @ExceptionHandler(EmptySearchException.class)
 public String handleEmptySearchException(EmptySearchException e){
  log.warn(e.getLocalizedMessage());
  return "redirect:/";
 }

}
