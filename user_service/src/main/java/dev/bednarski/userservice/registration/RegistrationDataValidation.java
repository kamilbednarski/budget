package dev.bednarski.userservice.registration;

public record RegistrationDataValidation(boolean isUsernameTaken, boolean isEmailTaken) { }
