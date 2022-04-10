package dev.bednarski.accountservice.exception.user;

public class UserNotFoundException extends RuntimeException {

  private static final String MESSAGE = "User could not be found.";

  public UserNotFoundException() {
    super(MESSAGE);
  }
}
