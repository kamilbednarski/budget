package dev.bednarski.registrationservice.exception;

import dev.bednarski.registrationservice.exception.email.EmailAlreadyTakenException;
import dev.bednarski.registrationservice.exception.email.MissingEmailException;
import dev.bednarski.registrationservice.exception.name.MissingFirstNameException;
import dev.bednarski.registrationservice.exception.name.MissingLastNameException;
import dev.bednarski.registrationservice.exception.password.MissingPasswordConfirmationException;
import dev.bednarski.registrationservice.exception.username.MissingUsernameException;
import dev.bednarski.registrationservice.exception.username.UsernameAlreadyTakenException;
import dev.bednarski.registrationservice.exception.username.UsernameTooShortException;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = {EmailAlreadyTakenException.class})
  public ResponseEntity<Object> handle(EmailAlreadyTakenException exception) {
    log.error("EmailAlreadyTakenException: ", exception);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }

  @ExceptionHandler(value = {MissingEmailException.class})
  public ResponseEntity<Object> handle(MissingEmailException exception) {
    log.error("MissingEmailException: ", exception);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }

  @ExceptionHandler(value = {MissingFirstNameException.class})
  public ResponseEntity<Object> handle(MissingFirstNameException exception) {
    log.error("MissingFirstNameException: ", exception);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }

  @ExceptionHandler(value = {MissingLastNameException.class})
  public ResponseEntity<Object> handle(MissingLastNameException exception) {
    log.error("MissingLastNameException: ", exception);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }

  @ExceptionHandler(value = {MissingPasswordConfirmationException.class})
  public ResponseEntity<Object> handle(MissingPasswordConfirmationException exception) {
    log.error("MissingPasswordConfirmationException: ", exception);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }

  @ExceptionHandler(value = {MissingUsernameException.class})
  public ResponseEntity<Object> handle(MissingUsernameException exception) {
    log.error("MissingUsernameException: ", exception);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }

  @ExceptionHandler(value = {UsernameAlreadyTakenException.class})
  public ResponseEntity<Object> handle(UsernameAlreadyTakenException exception) {
    log.error("UsernameAlreadyTakenException: ", exception);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }

  @ExceptionHandler(value = {UsernameTooShortException.class})
  public ResponseEntity<Object> handle(UsernameTooShortException exception) {
    log.error("UsernameTooShortException: ", exception);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }
}
