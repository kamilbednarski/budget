package dev.bednarski.accountservice.user;

public record UserPresenceResponse(boolean isUserPresent, Long userId) { }
