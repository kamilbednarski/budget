package dev.bednarski.registrationservice.token;

import static dev.bednarski.registrationservice.token.RegistrationTokenBuilder.aConfirmationToken;

import dev.bednarski.registrationservice.exception.token.ExpiredTokenException;
import dev.bednarski.registrationservice.exception.token.UnknownTokenException;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationTokenService {

  private final RegistrationTokenRepository repository;

  public String createTokenFor(Long userId) {
    String token = generateToken();
    RegistrationToken registrationToken = aConfirmationToken()
        .withToken(token)
        .withUserId(userId)
        .withDefaultExpirationDateTime()
        .build();
    repository.save(registrationToken);
    return token;
  }

  private String generateToken() {
    return UUID.randomUUID().toString();
  }

  public RegistrationToken confirmToken(String token) {
    RegistrationToken registrationToken = repository.findByToken(token)
        .orElseThrow(UnknownTokenException::new);
    validateExpirationDateTimeOf(registrationToken);
    registrationToken.setConfirmationDateTime(LocalDateTime.now());
    return registrationToken;
  }

  private void validateExpirationDateTimeOf(RegistrationToken token) {
    if (token.getExpirationDateTime().isBefore(LocalDateTime.now())) {
      throw new ExpiredTokenException();
    }
  }
}
