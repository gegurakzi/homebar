package io.malachai.homebar.application.command;

public record LoginCommand(String email, String password, boolean withRefreshToken) {}
