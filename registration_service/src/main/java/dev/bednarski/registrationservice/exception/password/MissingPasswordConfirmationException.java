package dev.bednarski.registrationservice.exception.password;

public class MissingPasswordConfirmationException extends RuntimeException {

  public static final String MESSAGE = "Registration form is missing a password confirmation.";

  public MissingPasswordConfirmationException() {
    super(MESSAGE);
  }
}
