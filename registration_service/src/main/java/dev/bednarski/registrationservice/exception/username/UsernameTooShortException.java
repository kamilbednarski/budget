package dev.bednarski.registrationservice.exception.username;

public class UsernameTooShortException extends RuntimeException {

  public static final String MESSAGE =
      "The username provided in the registration form is too short. Minimum length is 5.";

  public UsernameTooShortException() {
    super(MESSAGE);
  }
}
