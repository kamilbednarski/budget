package dev.bednarski.registrationservice;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

  public static final String USERNAME_EMAIL_EXCHANGE = "username-email-verification-exchange";
  public static final String USER_REGISTRATION_EXCHANGE = "user-registration-exchange";
  public static final String USERNAME_EMAIL_ROUTING_KEY = "username-email-verification-key";
  public static final String USER_REGISTRATION_ROUTING_KEY = "user-registration-key";

  @Bean
  public DirectExchange usernameEmailExchange() {
    return new DirectExchange(USERNAME_EMAIL_EXCHANGE);
  }

  @Bean
  public DirectExchange userRegistrationExchange() {
    return new DirectExchange(USER_REGISTRATION_EXCHANGE);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
