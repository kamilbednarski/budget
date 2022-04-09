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
    Long userId = registerAndReadUserId(request);
    String token = tokenService.createTokenFor(userId);

//  emailSender.send(user.getEmail(), createConfirmationEmailWithTokenForUser(user, token))
//  send email to confirm registration
  }

  private Long registerAndReadUserId(RegistrationRequest request) {
    RegistrationResponse response = Optional.ofNullable(sender.sendToRegister(request))
        .orElseThrow(UserServiceUnavailableException::new);
    return response.userId();
  }

  public void confirmRegistration(String token) {

  }
}
