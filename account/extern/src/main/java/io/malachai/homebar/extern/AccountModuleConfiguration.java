package io.malachai.homebar.extern;

import io.malachai.homebar.domain.LoginProcessor;
import io.malachai.homebar.domain.RegisterProcessor;
import io.malachai.homebar.extern.jwt.JwtTokenGenerator;
import io.malachai.homebar.extern.jwt.JwtTokenParser;
import io.malachai.homebar.extern.jwt.JwtTokenProperties;
import io.malachai.homebar.extern.jwt.JwtTokenReader;
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
