package dev.bednarski.userservice.user;

import dev.bednarski.userservice.registration.RegistrationRequest;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public record UserService(UserRepository repository) {

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
}
