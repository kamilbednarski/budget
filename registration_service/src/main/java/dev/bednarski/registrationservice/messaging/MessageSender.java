package dev.bednarski.registrationservice.messaging;

import dev.bednarski.registrationservice.registration.ActivationRequest;
import dev.bednarski.registrationservice.registration.ActivationResponse;
import dev.bednarski.registrationservice.registration.MailRequest;
import dev.bednarski.registrationservice.registration.RegistrationData;
import dev.bednarski.registrationservice.registration.RegistrationDataValidation;
import dev.bednarski.registrationservice.registration.RegistrationRequest;
import dev.bednarski.registrationservice.registration.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSender {

  private final RabbitTemplate template;
  private final DirectExchange dataValidationExchange;
  private final DirectExchange registrationExchange;
  private final TopicExchange confirmationMailExchange;
  private final DirectExchange userActivationExchange;

  public RegistrationDataValidation sendToValidate(RegistrationData request) {
    return template.convertSendAndReceiveAsType(
        dataValidationExchange.getName(),
        MessagingConfig.DATA_VALIDATION_KEY,
        request,
        new ParameterizedTypeReference<>() { });
  }

  public RegistrationResponse sendToRegister(RegistrationRequest request) {
    return template.convertSendAndReceiveAsType(
        registrationExchange.getName(),
        MessagingConfig.REGISTRATION_ROUTING_KEY,
        request,
        new ParameterizedTypeReference<>() { });
  }

  public void sendToPost(MailRequest request) {
    template.convertAndSend(
        confirmationMailExchange.getName(),
        MessagingConfig.CONFIRMATION_MAIL_KEY,
        request);
  }

  public ActivationResponse sentToActivateUser(ActivationRequest request) {
    return template.convertSendAndReceiveAsType(
        userActivationExchange.getName(),
        MessagingConfig.USER_ACTIVATION_KEY,
        request,
        new ParameterizedTypeReference<>() { });
  }
}
