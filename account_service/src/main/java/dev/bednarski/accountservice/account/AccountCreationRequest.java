package dev.bednarski.accountservice.account;

public record AccountCreationRequest(
    String username,
    String name) { }
