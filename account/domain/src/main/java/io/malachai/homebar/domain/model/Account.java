package io.malachai.homebar.domain.model;

import io.malachai.homebar.domain.event.LoginEvent;
import io.malachai.homebar.domain.event.RegisterConfirmedEvent;
import io.malachai.homebar.domain.event.VerifyEmailEvent;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "ACCOUNT")
@Getter
public class Account extends RootModel {

    public Account() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private String emailVerifyToken;
    private boolean emailVerified;
    private LocalDateTime joinedAt;
    @UpdateTimestamp private LocalDateTime lastModifiedAt;
    private LocalDateTime lastLoginAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Account(
            String email,
            String password,
            String nickname,
            boolean emailVerified,
            LocalDateTime joinedAt,
            LocalDateTime lastLoginAt,
            Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.emailVerifyToken = null;
        this.emailVerified = emailVerified;
        this.joinedAt = joinedAt;
        this.lastLoginAt = lastLoginAt;
        this.roles = roles;
    }

    public static Account register(String email, String password, String nickname) {
        Account account =
                new Account(
                        email,
                        password,
                        nickname,
                        false,
                        LocalDateTime.now(),
                        null,
                        Set.of(Role.USER));
        return account;
    }

    public void verifyEmail() {
        this.emailVerifyToken = UUID.randomUUID().toString();
        offerEvent(new VerifyEmailEvent(this.email));
    }

    public void confirmRegister() {
        this.emailVerified = true;
        offerEvent(new RegisterConfirmedEvent(this.email));
    }

    public void login() {
        this.lastLoginAt = LocalDateTime.now();
        offerEvent(new LoginEvent(this.email));
    }

    public boolean passwordEquals(String password) {
        return this.password.equals(password);
    }

    public void logout() {}
}
