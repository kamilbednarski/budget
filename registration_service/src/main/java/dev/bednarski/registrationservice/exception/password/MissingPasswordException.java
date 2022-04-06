package dev.bednarski.registrationservice.exception.password;

public class MissingPasswordException extends RuntimeException {

  public static final String MESSAGE = "Registration form is missing a password.";

  public MissingPasswordException() {
    super(MESSAGE);
  }
}
