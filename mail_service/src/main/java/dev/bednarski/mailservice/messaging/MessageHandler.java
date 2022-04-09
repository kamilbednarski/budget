package dev.bednarski.mailservice.messaging;

import dev.bednarski.mailservice.mail.MailRequest;
import dev.bednarski.mailservice.mail.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public record MessageHandler(MailService service) {

  @RabbitListener(queues = MessagingConfig.CONFIRMATION_MAIL_QUEUE)
  public void sendConfirmationEmail(MailRequest request) {
    service.createAndSendConfirmationEmail(request);
  }
}
