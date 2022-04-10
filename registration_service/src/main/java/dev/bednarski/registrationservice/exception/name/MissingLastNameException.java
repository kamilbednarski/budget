package dev.bednarski.registrationservice.exception.name;

public class MissingLastNameException extends RuntimeException {

  private static final String MESSAGE = "Registration form is missing a last name.";

  public MissingLastNameException() {
    super(MESSAGE);
  }
}
