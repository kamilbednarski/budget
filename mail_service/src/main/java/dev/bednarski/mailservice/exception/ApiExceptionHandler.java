package dev.bednarski.mailservice.exception;

import dev.bednarski.mailservice.exception.mail.EmailSendingFailureException;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = {EmailSendingFailureException.class})
  public ResponseEntity<Object> handle(EmailSendingFailureException exception) {
    log.error("EmailSendingFailureException: ", exception);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var responseBody = new ExceptionResponse(exception.getMessage(), status, ZonedDateTime.now());
    return new ResponseEntity<>(responseBody, status);
  }
}
