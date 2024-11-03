package io.malachai.homebar.presentation;

import io.malachai.homebar.presentation.request.LoginRequest;
import io.malachai.homebar.presentation.request.RegisterConfirmRequest;
import io.malachai.homebar.presentation.request.RegisterRequest;
import io.malachai.homebar.presentation.request.TokenReissueRequest;
import io.malachai.homebar.presentation.response.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public interface AuthSpecification {

    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody RegisterRequest request);

    @GetMapping("/confirm")
    ResponseEntity<Void> confirmRegister(RegisterConfirmRequest request);

    @PostMapping("/login")
    ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request);

    @PostMapping("/reissue")
    ResponseEntity<String> reissue(@RequestBody TokenReissueRequest request);
}
