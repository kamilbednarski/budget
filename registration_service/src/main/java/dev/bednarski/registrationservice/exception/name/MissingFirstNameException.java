package dev.bednarski.registrationservice.exception.name;

public class MissingFirstNameException extends RuntimeException {

  public static final String MESSAGE = "Registration form is missing a first name.";

  public MissingFirstNameException() {
    super(MESSAGE);
  }
}
