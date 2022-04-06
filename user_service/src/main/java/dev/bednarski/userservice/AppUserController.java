package dev.bednarski.userservice;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/app-user")
public record AppUserController(AppUserService appUserService, RabbitTemplate template) {

  @PostMapping("register")
  public void register(@RequestBody AppUserRegistrationRequest toRegister) {
    appUserService.register(toRegister);
  }
}
