package io.malachai.homebar.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.malachai.homebar.domain.model.Account;
import io.malachai.homebar.domain.model.AccountRepository;
import org.junit.jupiter.api.Test;

public class RegisterProcessorTest {

    @Test
    void registerTest() {
        // given
        final AccountRepository repositoryMock = mock(AccountRepository.class);
        final PasswordEncryptor encryptorMock = mock(PasswordEncryptor.class);

        final Account fixture = AccountFixture.a();

        var sut = new RegisterProcessor(repositoryMock, encryptorMock);

        // when
        var account =
                sut.register(fixture.getEmail(), fixture.getPassword(), fixture.getNickname());

        // then
        // 1. check if account is valid
        assertThat(account.getEmail()).isEqualTo(fixture.getEmail());
        assertThat(account.getNickname()).isEqualTo(fixture.getNickname());

        // 2. check if account is saved through repository
        verify(repositoryMock, atLeastOnce()).save(any());

        // 3. check if password is encrypted
        verify(encryptorMock, atLeastOnce()).encrypt(eq(fixture.getPassword()));
    }

    @Test
    void confirmRegisterTest() {
        // given
        final AccountRepository repositoryMock = mock(AccountRepository.class);
        final PasswordEncryptor encryptorMock = mock(PasswordEncryptor.class);

        final Account fixture = AccountFixture.a();

        when(repositoryMock.findByEmail(fixture.getEmail())).thenReturn(fixture);

        var sut = new RegisterProcessor(repositoryMock, encryptorMock);

        // when
        var account = sut.confirmRegister(fixture.getEmail(), fixture.getEmailVerifyToken());

        // then
        // 1. check if account is verified
        assertThat(account.isEmailVerified()).isTrue();

        // 2. check if status is updated
        verify(repositoryMock, atLeastOnce()).save(any());
    }
}
