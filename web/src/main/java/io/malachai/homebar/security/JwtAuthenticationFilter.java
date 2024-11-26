package io.malachai.homebar.security;

import io.malachai.homebar.domain.RawToken;
import io.malachai.homebar.domain.Role;
import io.malachai.homebar.domain.TokenParser;
import io.malachai.homebar.domain.TokenReader;
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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenReader tokenReader;
    private final TokenParser tokenParser;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        tokenReader
                .read(token)
                .ifPresent(
                        tokenString -> {
                            RawToken rawToken = tokenParser.parse(tokenString);
                            UsernamePasswordAuthenticationToken ctx =
                                    UsernamePasswordAuthenticationToken.authenticated(
                                            rawToken.principal(),
                                            token,
                                            authorities(rawToken.principal().roles()));
                            SecurityContextHolder.getContext().setAuthentication(ctx);
                        });
        filterChain.doFilter(request, response);
    }

    private Set<SimpleGrantedAuthority> authorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s", role.toString())))
                .collect(Collectors.toSet());
    }
}
