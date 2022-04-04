package dev.bednarski.account.exception;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

public record ExceptionResponse(
    String message,
    HttpStatus httpStatus,
    ZonedDateTime timestamp) { }
