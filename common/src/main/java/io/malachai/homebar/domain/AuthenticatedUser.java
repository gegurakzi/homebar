package io.malachai.homebar.domain;

import java.util.Set;

public record AuthenticatedUser(
        String email, String nickname, boolean emailVerified, Set<Role> roles) {}
