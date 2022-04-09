package dev.bednarski.accountservice.messaging;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

  public static final String USER_VERIFICATION_EXCHANGE = "user-verification-exchange";
  public static final String USER_VERIFICATION_KEY = "user-verification-key";

  @Bean
  public DirectExchange userVerificationExchange() {
    return new DirectExchange(USER_VERIFICATION_EXCHANGE);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}


