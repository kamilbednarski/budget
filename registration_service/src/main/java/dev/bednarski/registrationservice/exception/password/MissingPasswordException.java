package dev.bednarski.registrationservice.exception.password;

public class MissingPasswordException extends RuntimeException {

  private static final String MESSAGE = "Registration form is missing a password.";

  public MissingPasswordException() {
    super(MESSAGE);
  }
}
