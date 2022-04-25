package com.netcracker.DTO.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse {

 private HttpStatus httpStatus;

 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm--yyyy hh:mm:ss")
 private LocalDateTime localDateTime;

 private String messageUser;

 private String stackTrace;

 @Builder
 public ApiResponse(HttpStatus httpStatus, String message, String debugMessage) {
  this.httpStatus = httpStatus;
  this.localDateTime = LocalDateTime.now();
  this.messageUser = message;
  this.stackTrace = debugMessage;
 }

 public ApiResponse() {
  this.localDateTime = LocalDateTime.now();
 }

 public ApiResponse(HttpStatus httpStatus, String message, Throwable ex) {
  this();
  this.httpStatus = httpStatus;
  this.messageUser = message;
  stackTrace = ex.getLocalizedMessage();
 }

 @Override
 public String toString() {
  return "ApiResponse{" +
   "message='" + messageUser + '\'' +
   ", debugMessage='" + stackTrace + '\'' +
   '}';
 }


}
