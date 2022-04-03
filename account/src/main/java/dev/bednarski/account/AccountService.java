package dev.bednarski.account;

import static java.lang.Boolean.TRUE;

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
    Boolean isUserExisting = template.convertSendAndReceiveAsType(
        directExchange.getName(),
        MessagingConfig.ROUTING_KEY,
        toCreate.userId(),
        new ParameterizedTypeReference<>() {});

    if (TRUE.equals(isUserExisting)) {
      repository.save(
          Account.builder()
              .userId(toCreate.userId())
              .name(toCreate.name())
              .build());
    }
  }
}
