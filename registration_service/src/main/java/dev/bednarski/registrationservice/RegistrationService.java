package dev.bednarski.registrationservice;

import dev.bednarski.registrationservice.exception.connection.UserServiceUnavailableException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public record RegistrationService(DataValidator validator, MessageSender sender) {

  public void registerUserAndSendConfirmationEmail(RegistrationRequest request) {
    validator.validate(request);
    RegistrationResponse response = Optional.ofNullable(sender.send(request))
        .orElseThrow(UserServiceUnavailableException::new);

    System.out.println(response);
//    User user = anUser()
//        .withFirstName(request.getFirstName())
//        .withLastName(request.getLastName())
//        .withUsername(request.getUsername())
//        .withEmail(request.getEmail())
//        .withPassword(passwordService.encode(request.getPassword()))
//        .withUserRole(UserRole.USER)
//        .build();
//    userService.saveUser(user);
//    String token = createAndSaveConfirmationTokenForUser(user);
//    emailSender.send(user.getEmail(), createConfirmationEmailWithTokenForUser(user, token));

    //validate form
    //send message to check if unique data not taken and if not send to create user
    //send email to confirm registration
  }
}
