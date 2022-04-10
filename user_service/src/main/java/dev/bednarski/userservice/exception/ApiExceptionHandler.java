package dev.bednarski.userservice.exception;

import dev.bednarski.userservice.exception.user.UserAlreadyExistsException;
import dev.bednarski.userservice.exception.user.UserNotFoundException;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = {UserAlreadyExistsException.class})
  public ResponseEntity<Object> handle(UserAlreadyExistsException exception) {
    log.error("UserAlreadyExistsException: ", exception);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }

  @ExceptionHandler(value = {UserNotFoundException.class})
  public ResponseEntity<Object> handle(UserNotFoundException exception) {
    log.error("UserNotFoundException: ", exception);
    HttpStatus status = HttpStatus.NOT_FOUND;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }
}
