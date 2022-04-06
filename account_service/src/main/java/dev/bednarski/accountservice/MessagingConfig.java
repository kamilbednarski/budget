package dev.bednarski.accountservice;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

  public static final String EXCHANGE = "qpp-user-verification-exchange";
  public static final String ROUTING_KEY = "qpp-user-verification-exchange";

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(EXCHANGE);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}


