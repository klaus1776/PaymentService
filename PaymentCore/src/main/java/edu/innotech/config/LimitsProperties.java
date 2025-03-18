package edu.innotech.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integration.clients")
public class LimitsProperties {
    private final RestTemplateProperties limitsProperties;

    @ConstructorBinding
    public LimitsProperties(RestTemplateProperties limitsProperties) {
        this.limitsProperties = limitsProperties;
    }

    public RestTemplateProperties getLimitsProperties() {
        return limitsProperties;
    }
}
