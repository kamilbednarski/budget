package dev.bednarski.registrationservice.exception.email;

public class EmailAlreadyTakenException extends RuntimeException {

  private static final String MESSAGE =
      "The email address provided in the registration form is already taken.";

  public EmailAlreadyTakenException() {
    super(MESSAGE);
  }
}
