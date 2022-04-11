package com.netcracker.DTO.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


public class EntityResponse<T> {

  private HttpStatus httpStatus;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm--yyyy hh:mm:ss")
  private LocalDateTime localDateTime;

  private String message;

  private String debugMessage;

  private List<T> date;

  public EntityResponse() {
   this.localDateTime = LocalDateTime.now();
  }

  public EntityResponse(HttpStatus httpStatus, String message,Throwable ex) {
   this();
   this.httpStatus = httpStatus;
   this.message = message;
   debugMessage=ex.getLocalizedMessage();
  }

  @Override
  public String toString() {
   return "ApiResponse{" +
    "message='" + message + '\'' +
    ", debugMessage='" + debugMessage + '\'' +
    '}';
  }

  public HttpStatus getHttpStatus() {
   return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
   this.httpStatus = httpStatus;
  }

  public LocalDateTime getLocalDateTime() {
   return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
   this.localDateTime = localDateTime;
  }

  public String getMessage() {
   return message;
  }

  public void setMessage(String message) {
   this.message = message;
  }

  public String getDebugMessage() {
   return debugMessage;
  }

  public void setDebugMessage(String debugMessage) {
   this.debugMessage = debugMessage;
  }

  public List<T> getDate() {
   return date;
  }

  public void setDate(List<T> date) {
   this.date = date;
  }
 }
