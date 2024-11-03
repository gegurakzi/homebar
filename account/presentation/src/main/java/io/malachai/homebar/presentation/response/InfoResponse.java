package io.malachai.homebar.presentation.response;

import io.malachai.homebar.application.command.InfoCommand;

public record InfoResponse(Long id, String email, String nickname) {

    public static InfoResponse from(InfoCommand command) {
        return null;
    }
}
