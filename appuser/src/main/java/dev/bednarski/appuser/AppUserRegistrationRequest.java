package dev.bednarski.appuser;

public record AppUserRegistrationRequest(
    String firstName,
    String lastName,
    String email) { }
