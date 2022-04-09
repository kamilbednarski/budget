package dev.bednarski.registrationservice.registration;

public record RegistrationRequest(
    String firstName,
    String lastName,
    String username,
    String email,
    String password,
    String passwordConfirmation) { }
