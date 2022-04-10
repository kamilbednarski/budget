package dev.bednarski.userservice.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

  public static final String DATA_VALIDATION_QUEUE = "registration-data-validation-queue";
  public static final String DATA_VALIDATION_EXCHANGE = "registration-data-validation-exchange";
  public static final String DATA_VALIDATION_KEY = "registration-data-validation-key";

  public static final String REGISTRATION_QUEUE = "user-registration-key-queue";
  public static final String REGISTRATION_EXCHANGE = "user-registration-exchange";
  public static final String REGISTRATION_KEY = "user-registration-key";

  public static final String USER_VERIFICATION_QUEUE = "user-verification-queue";
  public static final String USER_VERIFICATION_EXCHANGE = "user-verification-exchange";
  public static final String USER_VERIFICATION_KEY = "user-verification-key";

  public static final String USER_ACTIVATION_QUEUE = "user-activation-queue";
  public static final String USER_ACTIVATION_EXCHANGE = "user-activation-exchange";
  public static final String USER_ACTIVATION_KEY = "user-activation-key";

  @Bean
  public Queue dataValidationQueue() {
    return new Queue(DATA_VALIDATION_QUEUE);
  }

  @Bean
  public DirectExchange dataValidationExchange() {
    return new DirectExchange(DATA_VALIDATION_EXCHANGE);
  }

  @Bean
  public Binding dataValidationBinding(
      Queue dataValidationQueue, DirectExchange dataValidationExchange) {
    return BindingBuilder
        .bind(dataValidationQueue)
        .to(dataValidationExchange)
        .with(DATA_VALIDATION_KEY);
  }

  @Bean
  public Queue userVerificationQueue() {
    return new Queue(USER_VERIFICATION_QUEUE);
  }

  @Bean
  public DirectExchange userVerificationExchange() {
    return new DirectExchange(USER_VERIFICATION_EXCHANGE);
  }

  @Bean
  public Binding userVerificationBinding(
      Queue userVerificationQueue, DirectExchange userVerificationExchange) {
    return BindingBuilder
        .bind(userVerificationQueue)
        .to(userVerificationExchange)
        .with(USER_VERIFICATION_KEY);
  }

  @Bean
  public Queue registrationQueue() {
    return new Queue(REGISTRATION_QUEUE);
  }

  @Bean
  public DirectExchange registrationExchange() {
    return new DirectExchange(REGISTRATION_EXCHANGE);
  }

  @Bean
  public Binding registrationBinding(Queue registrationQueue, DirectExchange registrationExchange) {
    return BindingBuilder
        .bind(registrationQueue)
        .to(registrationExchange)
        .with(REGISTRATION_KEY);
  }

  @Bean
  public Queue userActivationQueue() {
    return new Queue(USER_ACTIVATION_QUEUE);
  }

  @Bean
  public DirectExchange userActivationExchange() {
    return new DirectExchange(USER_ACTIVATION_EXCHANGE);
  }

  @Bean
  public Binding userActivationBinding(
      Queue userActivationQueue, DirectExchange userActivationExchange) {
    return BindingBuilder
        .bind(userActivationQueue)
        .to(userActivationExchange)
        .with(USER_ACTIVATION_KEY);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
