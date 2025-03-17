package edu.innotech.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({PaymentsProperties.class, LimitsProperties.class})
public class PaymentsConfig {
    private final PaymentsProperties paymentsProperties;
    private final LimitsProperties limitsProperties;

    public PaymentsConfig(PaymentsProperties paymentsProperties, LimitsProperties limitsProperties) {
        this.paymentsProperties = paymentsProperties;
        this.limitsProperties = limitsProperties;
    }

    @Bean
    public RestTemplate paymentsClient(RestTemplateBuilder builder, RestTemplateResponseErrorHandler errorHandler) {
        RestTemplateProperties paymentsProp = paymentsProperties.getPaymentsProperties();

        return builder//new RestTemplateBuilder()
                .rootUri(paymentsProp.getUri())
                .setConnectTimeout(paymentsProp.getConnectTimeout())
                .setReadTimeout(paymentsProp.getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }

    @Bean
    public RestTemplate limitsClient(RestTemplateBuilder builder, RestTemplateResponseErrorHandler errorHandler) {
        RestTemplateProperties limitsProp = limitsProperties.getLimitsProperties();

        return builder//new RestTemplateBuilder()
                .rootUri(limitsProp.getUri())
                .setConnectTimeout(limitsProp.getConnectTimeout())
                .setReadTimeout(limitsProp.getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }
}
