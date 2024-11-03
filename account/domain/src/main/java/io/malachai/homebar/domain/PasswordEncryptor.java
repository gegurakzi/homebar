package io.malachai.homebar.domain;

public interface PasswordEncryptor {

    String encrypt(String password);

    boolean matches(String rawPassword, String encryptedPassword);
}
