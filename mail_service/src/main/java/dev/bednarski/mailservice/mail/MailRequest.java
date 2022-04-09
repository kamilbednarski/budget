package dev.bednarski.mailservice.mail;

public record MailRequest(
    String recipientName,
    String email,
    String token) { }
