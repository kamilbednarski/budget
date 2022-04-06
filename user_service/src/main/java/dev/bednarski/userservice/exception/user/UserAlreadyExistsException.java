package dev.bednarski.userservice.exception.user;

public class UserAlreadyExistsException extends RuntimeException {

  private static final String DEFAULT_MESSAGE =
      "User with provided details already exists. Registration cannot proceed.";

  public UserAlreadyExistsException() {
    super(DEFAULT_MESSAGE);
  }
}
