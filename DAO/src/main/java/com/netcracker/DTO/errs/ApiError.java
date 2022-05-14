package com.netcracker.DTO.errs;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
public class ApiError extends Exception {

 @JsonInclude(JsonInclude.Include.NON_NULL)
 private HttpStatus httpStatus;

 @JsonInclude(JsonInclude.Include.NON_NULL)
 private String debugMessage;

 @JsonInclude(JsonInclude.Include.NON_NULL)
 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm--yyyy hh:mm:ss")
 private LocalDateTime localDateTime;

 @JsonInclude(JsonInclude.Include.NON_NULL)
 private String error_code;

 @JsonInclude(JsonInclude.Include.NON_NULL)
 private String messageUser;

 @JsonInclude(JsonInclude.Include.NON_NULL)
 private String stack;

 @Builder
 public ApiError(HttpStatus httpStatus, String message, String debugMessage) {
  super(message);
  this.httpStatus = httpStatus;
  this.localDateTime = LocalDateTime.now();
  this.messageUser = message;
  this.stack = stack;
 }

 public ApiError(String message) {
  super(message);
  this.localDateTime = LocalDateTime.now();
 }

 @Override
 public String toString() {
  return "ApiResponse{" +
   "message='" + messageUser + '\'' +
   ", debugMessage='" + stack + '\'' +
   '}';
 }

}
