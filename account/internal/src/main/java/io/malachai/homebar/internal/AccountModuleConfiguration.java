package io.malachai.homebar.internal;

import io.malachai.homebar.domain.LoginProcessor;
import io.malachai.homebar.domain.RegisterProcessor;
import io.malachai.homebar.internal.jwt.JwtTokenGenerator;
import io.malachai.homebar.internal.jwt.JwtTokenParser;
import io.malachai.homebar.internal.jwt.JwtTokenProperties;
import io.malachai.homebar.internal.jwt.JwtTokenReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    ApplicationDomainEventPublisher.class,
    RegisterProcessor.class,
    LoginProcessor.class,
    BCryptPasswordEncryptor.class,
    JwtTokenProperties.class,
    JwtTokenReader.class,
    JwtTokenGenerator.class,
    JwtTokenParser.class
})
public class AccountModuleConfiguration {}
