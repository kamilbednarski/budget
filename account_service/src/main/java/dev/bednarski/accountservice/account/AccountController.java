package dev.bednarski.accountservice.account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/account-service/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService service;

  @PostMapping
  public void createAccount(@RequestBody AccountCreationRequest toCreate) {
    service.createAccount(toCreate);
  }
}
