package dev.bednarski.registrationservice.registration;

import dev.bednarski.registrationservice.exception.connection.UserServiceUnavailableException;
import dev.bednarski.registrationservice.messaging.MessageSender;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public record RegistrationService(
    DataValidator validator,
    MessageSender sender,
    RegistrationTokenService tokenService) {

  public void register(RegistrationRequest request) {
    validator.validate(request);
    RegistrationResponse userData = registerAndReadUserData(request);
    String token = tokenService.createTokenFor(userData.id());
    sender.sendToPost(new MailRequest(userData.firstName(), userData.email(), token));
  }

  private RegistrationResponse registerAndReadUserData(RegistrationRequest request) {
    return Optional.ofNullable(sender.sendToRegister(request))
        .orElseThrow(UserServiceUnavailableException::new);
  }

  public void confirmRegistration(String token) {

  }
}
