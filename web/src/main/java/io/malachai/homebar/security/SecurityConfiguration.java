package io.malachai.homebar.security;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(
                        cors ->
                                cors.configurationSource(
                                        (req) -> {
                                            CorsConfiguration config = new CorsConfiguration();
                                            config.setAllowedHeaders(
                                                    Collections.singletonList("*"));
                                            config.setAllowedMethods(
                                                    Collections.singletonList("*"));
                                            config.setAllowedOriginPatterns(
                                                    Collections.singletonList(
                                                            "http://192.168.45.2:3000"));
                                            config.setAllowCredentials(true);
                                            return config;
                                        }))
                .httpBasic(basic -> basic.disable())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
