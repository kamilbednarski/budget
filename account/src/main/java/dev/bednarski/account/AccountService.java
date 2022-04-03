package dev.bednarski.account;

import static java.util.Objects.nonNull;

import dev.bednarski.account.exception.user.UserNotFoundException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public record AccountService(
    AccountRepository repository,
    RabbitTemplate template,
    DirectExchange directExchange) {

  public void createAccount(AccountCreationRequest toCreate) {
    boolean isUserPresent = sendQueryToCheckUserBy(toCreate.userId());
    if (!isUserPresent) {
      throw new UserNotFoundException();
    }
    repository.save(
        Account.builder()
            .userId(toCreate.userId())
            .name(toCreate.name())
            .build());

  }

  private boolean sendQueryToCheckUserBy(Long userId) {
    AppUserPresenceMessage responseMessage = template.convertSendAndReceiveAsType(
        directExchange.getName(),
        MessagingConfig.ROUTING_KEY,
        new AppUserPresenceMessage(userId, false),
        new ParameterizedTypeReference<>() { });
    return nonNull(responseMessage) && responseMessage.isUserPresent();
  }
}
