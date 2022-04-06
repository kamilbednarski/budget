package dev.bednarski.accountservice;

public record AppUserPresenceMessage(String username, boolean isUserPresent) { }
