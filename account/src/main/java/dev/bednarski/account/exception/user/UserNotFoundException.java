package dev.bednarski.account.exception.user;

public class UserNotFoundException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "User could not be found.";

  public UserNotFoundException() {
    super(DEFAULT_MESSAGE);
  }
}
