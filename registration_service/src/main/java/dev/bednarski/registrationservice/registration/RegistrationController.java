package dev.bednarski.registrationservice.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration-service/registration")
@RequiredArgsConstructor
public class RegistrationController {

  private final RegistrationService service;

  @PostMapping
  public ResponseEntity<Object> register(@RequestBody RegistrationRequest toRequest) {
    service.register(toRequest);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping(path = "/confirm")
  public ResponseEntity<Object> confirmRegistration(@RequestParam String token) {
    service.confirmRegistration(token);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
