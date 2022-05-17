package dev.bednarski.registrationservice.exception.token;

public class ExpiredTokenException extends RuntimeException {

  private static final String MESSAGE =
      "The token already expired and cannot be used to confirm the registration.";

  public ExpiredTokenException() {
    super(MESSAGE);
  }
}
