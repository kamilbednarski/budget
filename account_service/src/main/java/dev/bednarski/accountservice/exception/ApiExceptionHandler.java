package dev.bednarski.accountservice.exception;

import dev.bednarski.accountservice.exception.connection.UserServiceUnavailableException;
import dev.bednarski.accountservice.exception.user.UserNotFoundException;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = {UserNotFoundException.class})
  public ResponseEntity<Object> handle(UserNotFoundException exception) {
    log.error("UserNotFoundException: ", exception);
    HttpStatus status = HttpStatus.NOT_FOUND;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }

  @ExceptionHandler(value = {UserServiceUnavailableException.class})
  public ResponseEntity<Object> handle(UserServiceUnavailableException exception) {
    log.error("UserServiceUnavailableException: ", exception);
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }
}
