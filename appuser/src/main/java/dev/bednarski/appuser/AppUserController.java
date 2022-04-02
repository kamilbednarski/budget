package dev.bednarski.appuser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/app-user")
public record AppUserController(AppUserService appUserService) {

  @PostMapping("register")
  public void register(@RequestBody AppUserRegistrationRequest toRegister) {
    log.info("New to register {}", toRegister);
    appUserService.register(toRegister);
  }
}
