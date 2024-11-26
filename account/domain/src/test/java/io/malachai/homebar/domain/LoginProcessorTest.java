package io.malachai.homebar.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.malachai.homebar.domain.model.Account;
import io.malachai.homebar.domain.model.AccountRepository;
import org.junit.jupiter.api.Test;

public class LoginProcessorTest {

    @Test
    void loginSuccessfully() {
        // given
        final AccountRepository repositoryMock = mock(AccountRepository.class);
        final PasswordEncryptor encryptorMock = mock(PasswordEncryptor.class);

        final Account fixture = AccountFixture.a();

        when(repositoryMock.findByEmail(fixture.getEmail())).thenReturn(fixture);
        when(encryptorMock.matches(any(), any())).thenReturn(true);

        var sut = new LoginProcessor(repositoryMock, encryptorMock);

        // when
        var account = sut.login(fixture.getEmail(), fixture.getPassword());

        // then
        // 1. check if account is saved through repository
        verify(repositoryMock, atLeastOnce()).save(any());
    }

    @Test
    void loginWithWrongAccount() {
        // given
        final AccountRepository repositoryMock = mock(AccountRepository.class);
        final PasswordEncryptor encryptorMock = mock(PasswordEncryptor.class);

        final Account fixture = AccountFixture.a();

        when(repositoryMock.findByEmail(fixture.getEmail())).thenReturn(null);
        when(encryptorMock.matches(any(), any())).thenReturn(true);

        var sut = new LoginProcessor(repositoryMock, encryptorMock);

        // when, then
        // 1. check if AccountNotFoundException is thrown
        assertThatThrownBy(
                        () -> {
                            sut.login(fixture.getEmail(), fixture.getPassword());
                        })
                .isInstanceOf(AccountNotFoundException.class);
    }

    @Test
    void loginWithWrongPassword() {
        // given
        final AccountRepository repositoryMock = mock(AccountRepository.class);
        final PasswordEncryptor encryptorMock = mock(PasswordEncryptor.class);

        final Account fixture = AccountFixture.a();

        when(repositoryMock.findByEmail(fixture.getEmail())).thenReturn(fixture);
        when(encryptorMock.matches(any(), any())).thenReturn(false);

        var sut = new LoginProcessor(repositoryMock, encryptorMock);

        // when, then
        // 1. check if WrongPasswordException is thrown
        assertThatThrownBy(
                        () -> {
                            sut.login(fixture.getEmail(), fixture.getPassword() + " must be wrong");
                        })
                .isInstanceOf(WrongPasswordException.class);
    }
}
