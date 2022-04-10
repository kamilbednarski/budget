package dev.bednarski.mailservice.mail;

import dev.bednarski.mailservice.exception.mail.EmailSendingFailureException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {

  private static final String EMAIL_CONFIRMATION_SUBJECT = "Confirm your e-mail";
  private static final String EMAIL_SENDER = "hello@registration.com";

  private final JavaMailSender sender;
  private final MailBuilder builder;

  public void createAndSendConfirmationEmail(MailRequest request) {
    String content = builder.buildConfirmationEmail(request.recipientName(), request.token());
    send(request.email(), content);
  }

  @Async
  private void send(String emailAddress, String content) {
    try {
      MimeMessage mail = createMail(emailAddress, content);
      sender.send(mail);
    } catch (MessagingException exception) {
      throw new EmailSendingFailureException();
    }
  }

  private MimeMessage createMail(
      String emailAddress, String content) throws MessagingException {
    MimeMessage message = sender.createMimeMessage();
    var helper = new MimeMessageHelper(message, "utf-8");
    helper.setText(content, true);
    helper.setTo(emailAddress);
    helper.setSubject(EMAIL_CONFIRMATION_SUBJECT);
    helper.setFrom(EMAIL_SENDER);
    return message;
  }
}
