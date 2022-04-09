package dev.bednarski.registrationservice.token;

import java.time.LocalDateTime;

public final class RegistrationTokenBuilder {

  private static final long TOKEN_VALIDITY_DURATION = 15;
  private final LocalDateTime creationDateTime = LocalDateTime.now();
  private String token;
  private LocalDateTime expirationDateTime;
  private Long userId;

  private RegistrationTokenBuilder() {
  }

  public static RegistrationTokenBuilder aConfirmationToken() {
    return new RegistrationTokenBuilder();
  }

  public RegistrationTokenBuilder withToken(String token) {
    this.token = token;
    return this;
  }

  public RegistrationTokenBuilder withDefaultExpirationDateTime() {
    this.expirationDateTime = this.creationDateTime.plusMinutes(TOKEN_VALIDITY_DURATION);
    return this;
  }

  public RegistrationTokenBuilder withUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public RegistrationToken build() {
    var confirmationToken = new RegistrationToken();
    confirmationToken.setToken(token);
    confirmationToken.setCreationDateTime(creationDateTime);
    confirmationToken.setExpirationDateTime(expirationDateTime);
    confirmationToken.setUserId(userId);
    return confirmationToken;
  }
}
