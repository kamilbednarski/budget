package dev.bednarski.registrationservice.exception.connection;

public class UserServiceUnavailableException extends RuntimeException {

  public static final String MESSAGE =
      "User service did not respond with required message. Validation cannot be finished.";

  public UserServiceUnavailableException() {
    super(MESSAGE);
  }
}
