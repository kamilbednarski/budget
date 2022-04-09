package dev.bednarski.userservice.registration;

public record RegistrationResponse(
    Long id,
    String firstName,
    String lastName,
    String username,
    String email) { }
