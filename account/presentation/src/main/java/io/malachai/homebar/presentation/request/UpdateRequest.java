package io.malachai.homebar.presentation.request;

import io.malachai.homebar.application.command.UpdateCommand;

public record UpdateRequest(String nickname) {

    public UpdateCommand toCommand(String email) {
        return new UpdateCommand(email, this.nickname);
    }
}
