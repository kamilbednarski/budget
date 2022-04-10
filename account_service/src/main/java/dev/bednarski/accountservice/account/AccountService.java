package dev.bednarski.accountservice.account;

import dev.bednarski.accountservice.exception.connection.UserServiceUnavailableException;
import dev.bednarski.accountservice.exception.user.UserNotFoundException;
import dev.bednarski.accountservice.messaging.MessageSender;
import dev.bednarski.accountservice.user.UserPresenceResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository repository;
  private final MessageSender sender;

  public void createAccount(AccountCreationRequest request) {
    Long userId = verifyUserAndReadUserId(request.username());
    repository.save(
        Account.builder()
            .userId(userId)
            .name(request.name())
            .build());
  }

  private Long verifyUserAndReadUserId(String username) {
    UserPresenceResponse response = Optional.ofNullable(sender.sendToVerify(username))
        .orElseThrow(UserServiceUnavailableException::new);

    if (!response.isUserPresent()) {
      throw new UserNotFoundException();
    }
    return response.userId();
  }
}
