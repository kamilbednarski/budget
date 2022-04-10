package dev.bednarski.registrationservice.registration;

import static dev.bednarski.registrationservice.token.RegistrationTokenBuilder.aConfirmationToken;

import dev.bednarski.registrationservice.token.RegistrationToken;
import dev.bednarski.registrationservice.token.RegistrationTokenRepository;
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
}
