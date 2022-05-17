package dev.bednarski.registrationservice.token;

import static dev.bednarski.registrationservice.token.RegistrationTokenBuilder.aConfirmationToken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.bednarski.registrationservice.exception.token.ExpiredTokenException;
import dev.bednarski.registrationservice.exception.token.UnknownTokenException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationTokenServiceTest {

  private static final Long USER_ID = 1L;
  private static final String TOKEN = "bd41e43e-3f92-4dac-b4e2-bee4ac139e30";
  private static final String TOKEN_MISSING_MESSAGE = "Requested token does not exist.";
  private static final String TOKEN_EXPIRED_MESSAGE =
      "The token already expired and cannot be used to confirm the registration.";

  @Mock
  RegistrationTokenRepository repository;
  @InjectMocks
  private RegistrationTokenService service;

  @Test
  void shouldCreateTokenFor() {
    // when
    String generatedToken = service.createTokenFor(USER_ID);

    // then
    ArgumentCaptor<RegistrationToken> tokenCaptor =
        ArgumentCaptor.forClass(RegistrationToken.class);

    verify(repository).save(tokenCaptor.capture());

    RegistrationToken token = tokenCaptor.getValue();
    assertNotNull(token.getToken());
    assertNotNull(token.getUserId());
    assertNotNull(token.getExpirationDateTime());
    assertEquals(USER_ID, token.getUserId());
    assertEquals(token.getToken(), generatedToken);
  }

  @Test
  void shouldConfirmToken() {
    // given
    RegistrationToken token = aConfirmationToken()
        .withToken(TOKEN)
        .withUserId(USER_ID)
        .withDefaultExpirationDateTime()
        .build();

    when(repository.findByToken(TOKEN)).thenReturn(Optional.of(token));

    // when
    service.confirmToken(TOKEN);

    // then
    verify(repository).findByToken(TOKEN);

    assertNotNull(token.getConfirmationDateTime());
  }

  @Test
  void shouldThrowExceptionWhenTokenMissing() {
    // given
    when(repository.findByToken(TOKEN)).thenReturn(Optional.empty());

    // when
    Exception exception = Assertions.assertThrows(
        UnknownTokenException.class,
        () -> service.confirmToken(TOKEN));

    // then
    assertEquals(TOKEN_MISSING_MESSAGE, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenTokenExpired() {
    // given
    RegistrationToken token = aConfirmationToken()
        .withToken(TOKEN)
        .withUserId(USER_ID)
        .withDefaultExpirationDateTime()
        .build();

    token.setExpirationDateTime(token.getExpirationDateTime().minusDays(1));

    when(repository.findByToken(TOKEN)).thenReturn(Optional.of(token));

    // when
    Exception exception = Assertions.assertThrows(
        ExpiredTokenException.class,
        () -> service.confirmToken(TOKEN));

    // then
    assertEquals(TOKEN_EXPIRED_MESSAGE, exception.getMessage());
  }
}
