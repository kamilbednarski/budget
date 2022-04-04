package dev.bednarski.account;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/account")
public record AccountController(AccountService accountService) {


  @PostMapping("create")
  public void createAccount(@RequestBody AccountCreationRequest toCreate) {
    accountService.createAccount(toCreate);
  }
}
