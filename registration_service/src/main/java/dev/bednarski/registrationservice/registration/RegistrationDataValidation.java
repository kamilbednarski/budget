package dev.bednarski.registrationservice.registration;

public record RegistrationDataValidation(
    boolean isUsernameTaken,
    boolean isEmailTaken) { }
