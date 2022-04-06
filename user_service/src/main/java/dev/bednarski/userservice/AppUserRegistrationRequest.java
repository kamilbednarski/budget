package dev.bednarski.userservice;

public record AppUserRegistrationRequest(
    String firstName,
    String lastName,
    String username,
    String email) { }
