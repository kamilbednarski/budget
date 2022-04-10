package dev.bednarski.accountservice.messaging;

import dev.bednarski.accountservice.user.UserPresenceRequest;
import dev.bednarski.accountservice.user.UserPresenceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSender {

  private final RabbitTemplate template;
  private final DirectExchange userVerificationExchange;

  public UserPresenceResponse sendToVerify(String username) {
    return template.convertSendAndReceiveAsType(
        userVerificationExchange.getName(),
        MessagingConfig.USER_VERIFICATION_KEY,
        new UserPresenceRequest(username),
        new ParameterizedTypeReference<>() { });
  }
}
