package dev.bednarski.mailservice.messaging;

import dev.bednarski.mailservice.mail.MailRequest;
import dev.bednarski.mailservice.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageHandler {

  private final MailService service;

  @RabbitListener(queues = MessagingConfig.CONFIRMATION_MAIL_QUEUE)
  public void sendConfirmationEmail(MailRequest request) {
    service.createAndSendConfirmationEmail(request);
  }
}
