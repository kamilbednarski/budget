package dev.bednarski.registrationservice.messaging;

import dev.bednarski.registrationservice.registration.RegistrationData;
import dev.bednarski.registrationservice.registration.RegistrationDataValidation;
import dev.bednarski.registrationservice.registration.RegistrationRequest;
import dev.bednarski.registrationservice.registration.RegistrationResponse;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public record MessageSender(
    RabbitTemplate template,
    DirectExchange dataValidationExchange,
    DirectExchange registrationExchange) {

  public RegistrationDataValidation sendToValidate(RegistrationData request) {
    return template.convertSendAndReceiveAsType(
        dataValidationExchange.getName(),
        MessagingConfig.DATA_VALIDATION_KEY,
        request,
        new ParameterizedTypeReference<>() {
        });
  }

  public RegistrationResponse sendToRegister(RegistrationRequest request) {
    return template.convertSendAndReceiveAsType(
        registrationExchange.getName(),
        MessagingConfig.REGISTRATION_ROUTING_KEY,
        request,
        new ParameterizedTypeReference<>() {
        });
  }
}
