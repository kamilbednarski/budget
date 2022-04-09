package dev.bednarski.mailservice.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

  public static final String CONFIRMATION_MAIL_QUEUE = "confirmation-mail-queue";
  public static final String CONFIRMATION_MAIL_EXCHANGE = "confirmation-mail-exchange";
  public static final String CONFIRMATION_MAIL_KEY = "confirmation-mail-key";

  @Bean
  public Queue confirmationMailQueue() {
    return new Queue(CONFIRMATION_MAIL_QUEUE);
  }

  @Bean
  public TopicExchange confirmationMailExchange() {
    return new TopicExchange(CONFIRMATION_MAIL_EXCHANGE);
  }

  @Bean
  public Binding confirmationMailBinding(
      Queue confirmationMailQueue, TopicExchange confirmationMailExchange) {
    return BindingBuilder
        .bind(confirmationMailQueue)
        .to(confirmationMailExchange)
        .with(CONFIRMATION_MAIL_KEY);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
