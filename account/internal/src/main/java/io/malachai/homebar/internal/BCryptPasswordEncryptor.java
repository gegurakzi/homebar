package io.malachai.homebar.internal;

import io.malachai.homebar.domain.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class BCryptPasswordEncryptor implements PasswordEncryptor {

    private final BCryptPasswordEncoder encoder;

    @Override
    public String encrypt(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean matches(String rawPassword, String encryptedPassword) {
        return encoder.matches(rawPassword, encryptedPassword);
    }
}
