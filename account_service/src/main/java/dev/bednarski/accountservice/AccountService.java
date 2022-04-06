package dev.bednarski.accountservice;

import static java.util.Objects.nonNull;

import dev.bednarski.accountservice.exception.user.UserNotFoundException;
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
    boolean isUserPresent = sendQueryToCheckUserBy(toCreate.username());
    if (!isUserPresent) {
      throw new UserNotFoundException();
    }
    repository.save(
        Account.builder()
            .userId(toCreate.userId())
            .name(toCreate.name())
            .build());

  }

  private boolean sendQueryToCheckUserBy(String username) {
    AppUserPresenceMessage responseMessage = template.convertSendAndReceiveAsType(
        directExchange.getName(),
        MessagingConfig.ROUTING_KEY,
        new AppUserPresenceMessage(username, false),
        new ParameterizedTypeReference<>() { });
    return nonNull(responseMessage) && responseMessage.isUserPresent();
  }
}