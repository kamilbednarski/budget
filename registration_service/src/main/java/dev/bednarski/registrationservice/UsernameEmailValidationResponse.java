package dev.bednarski.registrationservice;

public record UsernameEmailValidationResponse(boolean isUsernameTaken, boolean isEmailTaken) { }
