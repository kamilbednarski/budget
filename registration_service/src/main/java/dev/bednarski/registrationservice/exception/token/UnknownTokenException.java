package dev.bednarski.registrationservice.exception.token;

public class UnknownTokenException extends RuntimeException {

  private static final String MESSAGE = "Requested token does not exist.";

  public UnknownTokenException() {
    super(MESSAGE);
  }
}
