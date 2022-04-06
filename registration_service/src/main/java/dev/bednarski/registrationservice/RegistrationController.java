package dev.bednarski.registrationservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration-service")
public record RegistrationController(RegistrationService registrationService) {

  @PostMapping(path = "/register")
  public ResponseEntity<Object> register(@RequestBody RegistrationRequest toRequest) {
    registrationService.register(toRequest);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(path = "/confirm")
  public ResponseEntity<Object> confirmRegistration(@RequestParam String token) {
    registrationService.confirmRegistration(token);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
