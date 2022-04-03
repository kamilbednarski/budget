package dev.bednarski.account.exception;

import dev.bednarski.account.exception.user.UserNotFoundException;
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
  public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
    log.error("UserNotFoundException: ", exception);
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(
        new ApiExceptionResponse(exception.getMessage(), httpStatus, ZonedDateTime.now()),
        httpStatus);
  }
}
