package dev.bednarski.registrationservice.exception.email;

public class MissingEmailException extends RuntimeException {

  public static final String MESSAGE = "Registration form is missing an email address.";

  public MissingEmailException() {
    super(MESSAGE);
  }
}
