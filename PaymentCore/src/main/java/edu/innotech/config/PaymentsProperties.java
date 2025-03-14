package edu.innotech.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integration.clients")
public class PaymentsProperties {
    private final RestTemplateProperties paymentsProperties;

    @ConstructorBinding
    public PaymentsProperties(RestTemplateProperties paymentsProperties) {
        this.paymentsProperties = paymentsProperties;
    }

    public RestTemplateProperties getPaymentsProperties() {
        return paymentsProperties;
    }
}
