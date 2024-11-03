package io.malachai.homebar.security;

import io.malachai.homebar.domain.model.Account;
import io.malachai.homebar.domain.model.AccountRepository;
import io.malachai.homebar.domain.model.Role;
import io.malachai.homebar.internal.jwt.JwtRawToken;
import io.malachai.homebar.internal.jwt.JwtTokenParser;
import io.malachai.homebar.internal.jwt.JwtTokenReader;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final AccountRepository accountRepository;

    private final JwtTokenReader tokenReader;
    private final JwtTokenParser tokenParser;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        tokenReader
                .read(token)
                .ifPresent(
                        tokenString -> {
                            JwtRawToken rawToken = tokenParser.parse(tokenString);
                            Account principal = accountRepository.findByEmail(rawToken.subject());

                            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                                UsernamePasswordAuthenticationToken ctx =
                                        UsernamePasswordAuthenticationToken.authenticated(
                                                principal.getEmail(),
                                                token,
                                                authorities(principal.getRoles()));
                                SecurityContextHolder.getContext().setAuthentication(ctx);
                            }
                        });

        filterChain.doFilter(request, response);
    }

    private Set<SimpleGrantedAuthority> authorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s", role.toString())))
                .collect(Collectors.toSet());
    }
}
