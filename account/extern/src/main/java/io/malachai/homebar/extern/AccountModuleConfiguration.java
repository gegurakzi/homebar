package io.malachai.homebar.extern;

import io.malachai.homebar.domain.LoginProcessor;
import io.malachai.homebar.domain.RegisterProcessor;
import io.malachai.homebar.domain.UpdateProcessor;
import io.malachai.homebar.extern.jwt.JwtTokenProperties;
import io.malachai.homebar.extern.jwt.JwtTokenUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    ApplicationDomainEventPublisher.class,
    RegisterProcessor.class,
    LoginProcessor.class,
    UpdateProcessor.class,
    BCryptPasswordEncryptor.class,
    JwtTokenProperties.class,
    JwtTokenUtil.class
})
public class AccountModuleConfiguration {}
