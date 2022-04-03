package dev.bednarski.appuser;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public record AppUserService(AppUserRepository repository) {

  public void register(AppUserRegistrationRequest toRegister) {
    AppUser appUser = AppUser.builder()
        .firstName(toRegister.firstName())
        .lastName(toRegister.lastName())
        .email(toRegister.email())
        .build();
    repository.save(appUser);
  }

  @RabbitListener(queues = MessagingConfig.QUEUE_NAME)
  public AppUserPresenceMessage isUserExisting(AppUserPresenceMessage message) {
    boolean isUserPresent = repository.existsById(message.userId());
    return new AppUserPresenceMessage(message.userId(), isUserPresent);
  }
}
