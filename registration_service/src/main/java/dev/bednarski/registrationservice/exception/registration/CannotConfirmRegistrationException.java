package dev.bednarski.registrationservice.exception.registration;

public class CannotConfirmRegistrationException extends RuntimeException {

  public static final String MESSAGE = "User registration cannot be confirmed.";

  public CannotConfirmRegistrationException() {
    super(MESSAGE);
  }
}
