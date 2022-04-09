package dev.bednarski.registrationservice.exception.email;

public class InvalidEmailFormatException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "E-mail address format is incorrect.";

  public InvalidEmailFormatException() {
    super(DEFAULT_MESSAGE);
  }
}
