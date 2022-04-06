package dev.bednarski.userservice;

import dev.bednarski.userservice.exception.user.UserAlreadyExistsException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public record AppUserService(AppUserRepository repository) {

  public void register(AppUserRegistrationRequest toRegister) {
    if (isUserExisting(toRegister.username())) {
      throw new UserAlreadyExistsException();
    }
    AppUser appUser = AppUser.builder()
        .firstName(toRegister.firstName())
        .lastName(toRegister.lastName())
        .username(toRegister.username())
        .email(toRegister.email())
        .build();
    repository.save(appUser);
  }

  private boolean isUserExisting(String username) {
    return repository.existsByUsername(username);
  }

  @RabbitListener(queues = MessagingConfig.QUEUE_NAME)
  public AppUserPresenceMessage isUserExisting(AppUserPresenceMessage message) {
    return new AppUserPresenceMessage(message.username(), isUserExisting(message.username()));
  }
}
