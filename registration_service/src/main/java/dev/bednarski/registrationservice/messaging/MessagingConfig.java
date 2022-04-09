package dev.bednarski.registrationservice.messaging;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

  public static final String DATA_VALIDATION_EXCHANGE = "registration-data-validation-exchange";
  public static final String DATA_VALIDATION_KEY = "registration-data-validation-key";

  public static final String REGISTRATION_EXCHANGE = "user-registration-exchange";
  public static final String REGISTRATION_ROUTING_KEY = "user-registration-key";

  public static final String CONFIRMATION_MAIL_EXCHANGE = "confirmation-mail-exchange";
  public static final String CONFIRMATION_MAIL_KEY = "confirmation-mail-key";

  @Bean
  public DirectExchange dataValidationExchange() {
    return new DirectExchange(DATA_VALIDATION_EXCHANGE);
  }

  @Bean
  public DirectExchange registrationExchange() {
    return new DirectExchange(REGISTRATION_EXCHANGE);
  }

  @Bean
  public TopicExchange confirmationMailExchange() {
    return new TopicExchange(CONFIRMATION_MAIL_EXCHANGE);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
