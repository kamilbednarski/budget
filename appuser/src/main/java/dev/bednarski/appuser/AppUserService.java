package dev.bednarski.appuser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record AppUserService(AppUserRepository repository) {

  public void register(AppUserRegistrationRequest toRegister) {
    AppUser appUser = AppUser.builder()
        .firstName(toRegister.firstName())
        .lastName(toRegister.lastName())
        .email(toRegister.email())
        .build();
    repository.save(appUser);
  }
}
