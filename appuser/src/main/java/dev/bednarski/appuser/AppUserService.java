package dev.bednarski.appuser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
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

  @RabbitListener(queues = MessagingConfig.QUEUE_NAME, concurrency = "3")
  public boolean isUserExisting(Long userId) {
    log.info("YEEEES! USER ID: " + userId);
    return true;
  }
}
