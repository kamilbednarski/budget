package dev.bednarski.userservice.messaging;

import dev.bednarski.userservice.registration.RegistrationData;
import dev.bednarski.userservice.registration.RegistrationDataValidation;
import dev.bednarski.userservice.registration.RegistrationRequest;
import dev.bednarski.userservice.registration.RegistrationResponse;
import dev.bednarski.userservice.user.User;
import dev.bednarski.userservice.user.UserPresenceRequest;
import dev.bednarski.userservice.user.UserPresenceResponse;
import dev.bednarski.userservice.user.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageHandler {

  private final UserService service;

  @RabbitListener(queues = MessagingConfig.USER_VERIFICATION_QUEUE)
  public UserPresenceResponse verify(UserPresenceRequest request) {
    Optional<User> user = service.findByUsername(request.username());
    boolean isUserPresent = user.isPresent();
    Long userId = isUserPresent ? user.get().getId() : null;
    return new UserPresenceResponse(isUserPresent, userId);
  }

  @RabbitListener(queues = MessagingConfig.DATA_VALIDATION_QUEUE)
  public RegistrationDataValidation validate(RegistrationData data) {
    return new RegistrationDataValidation(
        service.isUsernameTaken(data.username()), service.isEmailTaken(data.email()));
  }

  @RabbitListener(queues = MessagingConfig.REGISTRATION_QUEUE)
  public RegistrationResponse register(RegistrationRequest request) {
    User user = service.registerFrom(request);
    return new RegistrationResponse(
        user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getUsername(),
        user.getEmail());
  }
}
