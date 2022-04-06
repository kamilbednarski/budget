package dev.bednarski.registrationservice;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;

public record MessageSender(
    RabbitTemplate template,
    DirectExchange directExchange) {

  public UsernameEmailValidationResponse send(UsernameEmailValidationRequest request) {
    return template.convertSendAndReceiveAsType(
        directExchange.getName(),
        MessagingConfig.USERNAME_EMAIL_ROUTING_KEY,
        request,
        new ParameterizedTypeReference<>() {
        });
  }

  public RegistrationResponse send(RegistrationRequest request) {
    return template.convertSendAndReceiveAsType(
        directExchange.getName(),
        MessagingConfig.USER_REGISTRATION_ROUTING_KEY,
        request,
        new ParameterizedTypeReference<>() {
        });
  }
}
