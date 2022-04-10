package dev.bednarski.registrationservice.registration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import dev.bednarski.registrationservice.exception.email.EmailAlreadyTakenException;
import dev.bednarski.registrationservice.exception.email.InvalidEmailFormatException;
import dev.bednarski.registrationservice.exception.email.MissingEmailException;
import dev.bednarski.registrationservice.exception.name.MissingFirstNameException;
import dev.bednarski.registrationservice.exception.name.MissingLastNameException;
import dev.bednarski.registrationservice.exception.password.MissingPasswordConfirmationException;
import dev.bednarski.registrationservice.exception.password.MissingPasswordException;
import dev.bednarski.registrationservice.exception.password.PasswordAndConfirmationNotEqualException;
import dev.bednarski.registrationservice.exception.username.MissingUsernameException;
import dev.bednarski.registrationservice.exception.username.UsernameAlreadyTakenException;
import dev.bednarski.registrationservice.messaging.MessageSender;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DataValidatorTest {

  private static final String EMPTY_STRING = "";
  private static final String FIRST_NAME = "First";
  private static final String LAST_NAME = "Last";
  private static final String USERNAME = "username";
  private static final String EMAIL = "example@example.com";
  private static final String INVALID_EMAIL = "example@example";
  private static final String PASSWORD = "Password123";
  private static final String PASSWORD_CONFIRMATION = "Password123";
  private static final String SALT = "abc";

  private static final String MISSING_FIRST_NAME_MESSAGE =
      "Registration form is missing a first name.";
  private static final String MISSING_LAST_NAME_MESSAGE =
      "Registration form is missing a last name.";
  private static final String MISSING_USERNAME_MESSAGE = "Registration form is missing an username.";
  private static final String MISSING_EMAIL_MESSAGE =
      "Registration form is missing an email address.";
  private static final String MISSING_PASSWORD_MESSAGE = "Registration form is missing a password.";
  private static final String MISSING_PASSWORD_CONFIRMATION_MESSAGE =
      "Registration form is missing a password confirmation.";
  private static final String PASSWORD_AND_CONFIRMATION_NOT_EQUAL_MESSAGE =
      "Password and its confirmation do not match.";
  private static final String INVALID_EMAIL_MESSAGE = "E-mail address format is incorrect.";
  private static final String EMAIL_TAKEN_MESSAGE =
      "The email address provided in the registration form is already taken.";
  private static final String USERNAME_TAKEN_MESSAGE =
      "The username provided in the registration form is already taken.";

  @InjectMocks
  private DataValidator validator;
  @Mock
  private MessageSender sender;

  @Test
  void shouldThrowExceptionWhenEmailTaken() {
    // given
    RegistrationRequest request = createSampleRequest();
    when(sender.sendToValidate(new RegistrationData(USERNAME, EMAIL)))
        .thenReturn(new RegistrationDataValidation(false, true));

    // when
    Exception exception = assertThrows(
        RuntimeException.class,
        () -> validator.validate(request));

    // then
    assertThat(exception).isInstanceOf(EmailAlreadyTakenException.class)
        .hasMessage(EMAIL_TAKEN_MESSAGE);
  }

  private RegistrationRequest createSampleRequest() {
    return new RegistrationRequest(
        FIRST_NAME,
        LAST_NAME,
        USERNAME,
        EMAIL,
        PASSWORD,
        PASSWORD_CONFIRMATION);
  }

  @Test
  void shouldThrowExceptionWhenUsernameTaken() {
    // given
    RegistrationRequest request = createSampleRequest();
    when(sender.sendToValidate(new RegistrationData(USERNAME, EMAIL)))
        .thenReturn(new RegistrationDataValidation(true, false));

    // when
    Exception exception = assertThrows(
        RuntimeException.class,
        () -> validator.validate(request));

    // then
    assertThat(exception).isInstanceOf(UsernameAlreadyTakenException.class)
        .hasMessage(USERNAME_TAKEN_MESSAGE);
  }

  @ParameterizedTest
  @MethodSource("provideInvalidRegistrationRequests")
  void shouldThrowExceptionForInvalidData(
      RegistrationRequest invalidRequest,
      Class<? extends RuntimeException> expectedException,
      String expectedMessage) {
    // when
    Exception exception = assertThrows(
        RuntimeException.class,
        () -> validator.validate(invalidRequest));

    // then
    assertThat(exception).isInstanceOf(expectedException)
        .hasMessage(expectedMessage);
  }

  private static Stream<Arguments> provideInvalidRegistrationRequests() {
    return Stream.of(
        caseNullFirstName(),
        caseEmptyFirstName(),
        caseNullLastName(),
        caseEmptyLastName(),
        caseNullUsername(),
        caseEmptyUsername(),
        caseNullEmail(),
        caseEmptyEmail(),
        caseNullPassword(),
        caseEmptyPassword(),
        caseNullPasswordConfirmation(),
        caseEmptyPasswordConfirmation(),
        caseNotMatchingPasswordAndConfirmation(),
        caseInvalidEmailFormat()
    );
  }

  private static Arguments caseNullFirstName() {
    return Arguments.of(
        new RegistrationRequest(
            null,
            LAST_NAME,
            USERNAME,
            EMAIL,
            PASSWORD,
            PASSWORD_CONFIRMATION),
        MissingFirstNameException.class,
        MISSING_FIRST_NAME_MESSAGE
    );
  }

  private static Arguments caseEmptyFirstName() {
    return Arguments.of(
        new RegistrationRequest(
            EMPTY_STRING,
            LAST_NAME,
            USERNAME,
            EMAIL,
            PASSWORD,
            PASSWORD_CONFIRMATION),
        MissingFirstNameException.class,
        MISSING_FIRST_NAME_MESSAGE
    );
  }

  private static Arguments caseNullLastName() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            null,
            USERNAME,
            EMAIL,
            PASSWORD,
            PASSWORD_CONFIRMATION),
        MissingLastNameException.class,
        MISSING_LAST_NAME_MESSAGE
    );
  }

  private static Arguments caseEmptyLastName() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            EMPTY_STRING,
            USERNAME,
            EMAIL,
            PASSWORD,
            PASSWORD_CONFIRMATION),
        MissingLastNameException.class,
        MISSING_LAST_NAME_MESSAGE
    );
  }

  private static Arguments caseNullUsername() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            LAST_NAME,
            null,
            EMAIL,
            PASSWORD,
            PASSWORD_CONFIRMATION),
        MissingUsernameException.class,
        MISSING_USERNAME_MESSAGE
    );
  }

  private static Arguments caseEmptyUsername() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            LAST_NAME,
            EMPTY_STRING,
            EMAIL,
            PASSWORD,
            PASSWORD_CONFIRMATION),
        MissingUsernameException.class,
        MISSING_USERNAME_MESSAGE
    );
  }

  private static Arguments caseNullEmail() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            LAST_NAME,
            USERNAME,
            null,
            PASSWORD,
            PASSWORD_CONFIRMATION),
        MissingEmailException.class,
        MISSING_EMAIL_MESSAGE
    );
  }

  private static Arguments caseEmptyEmail() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            LAST_NAME,
            USERNAME,
            EMPTY_STRING,
            PASSWORD,
            PASSWORD_CONFIRMATION),
        MissingEmailException.class,
        MISSING_EMAIL_MESSAGE
    );
  }

  private static Arguments caseNullPassword() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            LAST_NAME,
            USERNAME,
            EMAIL,
            null,
            PASSWORD_CONFIRMATION),
        MissingPasswordException.class,
        MISSING_PASSWORD_MESSAGE
    );
  }

  private static Arguments caseEmptyPassword() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            LAST_NAME,
            USERNAME,
            EMAIL,
            EMPTY_STRING,
            PASSWORD_CONFIRMATION),
        MissingPasswordException.class,
        MISSING_PASSWORD_MESSAGE
    );
  }

  private static Arguments caseNullPasswordConfirmation() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            LAST_NAME,
            USERNAME,
            EMAIL,
            PASSWORD,
            null),
        MissingPasswordConfirmationException.class,
        MISSING_PASSWORD_CONFIRMATION_MESSAGE
    );
  }

  private static Arguments caseEmptyPasswordConfirmation() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            LAST_NAME,
            USERNAME,
            EMAIL,
            PASSWORD,
            EMPTY_STRING),
        MissingPasswordConfirmationException.class,
        MISSING_PASSWORD_CONFIRMATION_MESSAGE
    );
  }

  private static Arguments caseNotMatchingPasswordAndConfirmation() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            LAST_NAME,
            USERNAME,
            EMAIL,
            PASSWORD,
            PASSWORD_CONFIRMATION + SALT),
        PasswordAndConfirmationNotEqualException.class,
        PASSWORD_AND_CONFIRMATION_NOT_EQUAL_MESSAGE
    );
  }

  private static Arguments caseInvalidEmailFormat() {
    return Arguments.of(
        new RegistrationRequest(
            FIRST_NAME,
            LAST_NAME,
            USERNAME,
            INVALID_EMAIL,
            PASSWORD,
            PASSWORD_CONFIRMATION),
        InvalidEmailFormatException.class,
        INVALID_EMAIL_MESSAGE
    );
  }
}