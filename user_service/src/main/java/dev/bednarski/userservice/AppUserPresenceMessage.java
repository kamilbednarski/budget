package dev.bednarski.userservice;

public record AppUserPresenceMessage(String username, boolean isUserPresent) { }
