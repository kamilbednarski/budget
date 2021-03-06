package dev.bednarski.userservice.user;

import dev.bednarski.userservice.exception.user.UserNotFoundException;
import dev.bednarski.userservice.registration.ActivationRequest;
import dev.bednarski.userservice.registration.RegistrationRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public User registerFrom(RegistrationRequest request) {
    User user = User.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .username(request.username())
        .email(request.email())
        .password(request.password())
        .build();
    repository.save(user);
    return user;
  }

  public boolean isUsernameTaken(String username) {
    return repository.existsByUsername(username);
  }

  public boolean isEmailTaken(String email) {
    return repository.existsByEmail(email);
  }

  public Optional<User> findByUsername(String username) {
    return repository.findByUsername(username);
  }

  @Transactional
  public boolean activateUser(ActivationRequest request) {
    User user = repository.findById(request.userId()).orElseThrow(UserNotFoundException::new);
    if (!user.isActive()) {
      user.setActive(true);
    }
    return user.isActive();
  }
}
