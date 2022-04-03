package dev.bednarski.account;

public record AppUserPresenceMessage(Long userId, boolean isUserPresent) { }
