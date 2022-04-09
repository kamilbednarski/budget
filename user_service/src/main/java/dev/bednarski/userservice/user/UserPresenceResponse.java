package dev.bednarski.userservice.user;

public record UserPresenceResponse(boolean isUserPresent, Long userId) { }
