package dev.bednarski.registrationservice.registration;

public record MailRequest(
    String recipientName,
    String email,
    String token) { }
