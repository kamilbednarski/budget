package dev.bednarski.registrationservice.exception.password;

public class PasswordAndConfirmationNotEqualException extends RuntimeException {

  private static final String MESSAGE = "Password and its confirmation do not match.";

  public PasswordAndConfirmationNotEqualException() {
    super(MESSAGE);
  }
}
