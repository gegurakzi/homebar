package io.malachai.homebar.presentation;

import io.malachai.homebar.application.executor.AccountCommandExecutor;
import io.malachai.homebar.presentation.request.LoginRequest;
import io.malachai.homebar.presentation.request.RegisterConfirmRequest;
import io.malachai.homebar.presentation.request.RegisterRequest;
import io.malachai.homebar.presentation.request.TokenReissueRequest;
import io.malachai.homebar.presentation.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApi implements AuthSpecification {

    private final AccountCommandExecutor executor;

    @Override
    public ResponseEntity<Void> register(RegisterRequest request) {
        executor.register(request.toCommand());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> confirmRegister(RegisterConfirmRequest request) {
        executor.confirmRegister(request.toCommand());
        return null;
    }

    @Override
    public ResponseEntity<TokenResponse> login(LoginRequest request) {
        var tokens = executor.login(request.toCommand());
        return new ResponseEntity<>(
                new TokenResponse(tokens.accessToken(), tokens.refreshToken()),
                HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> reissue(TokenReissueRequest request) {
        return null;
    }
}
