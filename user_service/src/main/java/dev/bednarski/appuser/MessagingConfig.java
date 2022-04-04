package dev.bednarski.appuser;

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

  public static final String QUEUE_NAME = "app-user-queue";
  public static final String EXCHANGE = "qpp-user-verification-exchange";
  public static final String ROUTING_KEY = "qpp-user-verification-exchange";

  @Bean
  public Queue queue() {
    return new Queue(QUEUE_NAME);
  }

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(EXCHANGE);
  }

  @Bean
  public Binding binding(Queue queue, DirectExchange directExchange) {
    return BindingBuilder
        .bind(queue)
        .to(directExchange)
        .with(ROUTING_KEY);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
