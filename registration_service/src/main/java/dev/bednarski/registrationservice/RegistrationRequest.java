package dev.bednarski.registrationservice;

public record RegistrationRequest(
    String firstName,
    String lastName,
    String username,
    String email,
    String password,
    String passwordConfirmation) { }
