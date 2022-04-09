package dev.bednarski.mailservice.exception.mail;

public class EmailSendingFailureException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "E-mail could not be sent.";

  public EmailSendingFailureException() {
    super(DEFAULT_MESSAGE);
  }
}
