package dev.bednarski.accountservice;

public record AccountCreationRequest(
    Long userId,
    String username,
    String name) { }
