package dev.bednarski.registrationservice.exception.username;

public class MissingUsernameException extends RuntimeException {

  public static final String MESSAGE = "Registration form is missing an username.";

  public MissingUsernameException() {
    super(MESSAGE);
  }
}
