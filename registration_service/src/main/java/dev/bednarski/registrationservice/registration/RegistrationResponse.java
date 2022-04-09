package dev.bednarski.registrationservice.registration;

public record RegistrationResponse(
    Long id,
    String firstName,
    String lastName,
    String username,
    String email) { }
