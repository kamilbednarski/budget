package dev.bednarski.registrationservice.exception.username;

public class UsernameAlreadyTakenException extends RuntimeException {

  public static final String MESSAGE =
      "The username provided in the registration form is already taken.";

  public UsernameAlreadyTakenException() {
    super(MESSAGE);
  }
}
