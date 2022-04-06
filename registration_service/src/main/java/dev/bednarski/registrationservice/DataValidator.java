package dev.bednarski.registrationservice;

import static java.util.Objects.isNull;

import dev.bednarski.registrationservice.exception.connection.UserServiceUnavailableException;
import dev.bednarski.registrationservice.exception.email.EmailAlreadyTakenException;
import dev.bednarski.registrationservice.exception.email.MissingEmailException;
import dev.bednarski.registrationservice.exception.name.MissingFirstNameException;
import dev.bednarski.registrationservice.exception.name.MissingLastNameException;
import dev.bednarski.registrationservice.exception.password.MissingPasswordConfirmationException;
import dev.bednarski.registrationservice.exception.password.MissingPasswordException;
import dev.bednarski.registrationservice.exception.password.PasswordAndConfirmationNotEqualException;
import dev.bednarski.registrationservice.exception.username.MissingUsernameException;
import dev.bednarski.registrationservice.exception.username.UsernameAlreadyTakenException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public record DataValidator(MessageSender messageSender) {

  public void validate(RegistrationRequest request) {
    validateCompletenessAndFormOf(request);
    validateUniquenessOf(request.username(), request.email());
  }

  private void validateCompletenessAndFormOf(RegistrationRequest request) {
    if (isNullOrEmpty(request.firstName())) {
      throw new MissingFirstNameException();
    } else if (isNullOrEmpty(request.lastName())) {
      throw new MissingLastNameException();
    } else if (isNullOrEmpty(request.username())) {
      throw new MissingUsernameException();
    } else if (isNullOrEmpty(request.email())) {
      throw new MissingEmailException();
    } else if (isNullOrEmpty(request.password())) {
      throw new MissingPasswordException();
    } else if (isNullOrEmpty(request.passwordConfirmation())) {
      throw new MissingPasswordConfirmationException();
    } else if (!request.password().equals(request.passwordConfirmation())) {
      throw new PasswordAndConfirmationNotEqualException();
    }
  }

  private boolean isNullOrEmpty(String data) {
    return isNull(data) || data.isEmpty();
  }

  private void validateUniquenessOf(String username, String email) {
    UsernameEmailValidationResponse response =
        Optional.ofNullable(messageSender.send(new UsernameEmailValidationRequest(username, email)))
            .orElseThrow(UserServiceUnavailableException::new);

    if (response.isEmailTaken()) {
      throw new EmailAlreadyTakenException();
    } else if (response.isUsernameTaken()) {
      throw new UsernameAlreadyTakenException();
    }
  }
}
