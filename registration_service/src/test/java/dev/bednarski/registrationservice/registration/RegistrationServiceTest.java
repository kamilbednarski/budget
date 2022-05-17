package dev.bednarski.registrationservice.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.bednarski.registrationservice.messaging.MessageSender;
import dev.bednarski.registrationservice.token.RegistrationTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

  private static final String FIRST_NAME = "John";
  private static final String LAST_NAME = "Wayne";
  private static final String USERNAME = "jWayne90";
  private static final String EMAIL = "john@wayne.com";
  private static final String PASSWORD = "00ca0a1e-cf64-40ab-aa63-2396290a59a9";
  private static final String PASSWORD_CONFIRMATION = "00ca0a1e-cf64-40ab-aa63-2396290a59a9";
  private static final String TOKEN = "cbd36fb3-279d-41e8-8926-601d01df4beb";
  private static final long ID = 1L;

  @InjectMocks
  RegistrationService service;
  @Mock
  private DataValidator validator;
  @Mock
  private MessageSender sender;
  @Mock
  private RegistrationTokenService tokenService;

  @Test
  void shouldRegister() {
    // given
    var request = new RegistrationRequest(
        FIRST_NAME, LAST_NAME, USERNAME, EMAIL, PASSWORD, PASSWORD_CONFIRMATION);
    var userData = new RegistrationResponse(ID, FIRST_NAME, LAST_NAME, USERNAME, EMAIL);

    when(sender.sendToRegister(request)).thenReturn(userData);
    when(tokenService.createTokenFor(ID)).thenReturn(TOKEN);

    // when
    service.register(request);

    // then
    verify(validator).validate(request);
    verify(sender).sendToRegister(request);
    verify(tokenService).createTokenFor(userData.id());

    ArgumentCaptor<MailRequest> mailRequestCaptor = ArgumentCaptor.forClass(MailRequest.class);
    verify(sender).sendToPost(mailRequestCaptor.capture());

    MailRequest mailRequest = mailRequestCaptor.getValue();
    assertEquals(userData.firstName(), mailRequest.recipientName());
    assertEquals(userData.email(), mailRequest.email());
    assertEquals(TOKEN, mailRequest.token());
  }

  @Test
  void shouldConfirmRegistration() {

  }
}