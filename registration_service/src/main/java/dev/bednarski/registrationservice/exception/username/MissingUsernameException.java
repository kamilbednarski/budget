package dev.bednarski.registrationservice.exception.username;

public class MissingUsernameException extends RuntimeException {

  private static final String MESSAGE = "Registration form is missing an username.";

  public MissingUsernameException() {
    super(MESSAGE);
  }
}
