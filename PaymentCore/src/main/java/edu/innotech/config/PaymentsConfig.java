package edu.innotech.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(PaymentsProperties.class)
public class PaymentsConfig {
    private final PaymentsProperties paymentsProperties;

    public PaymentsConfig(PaymentsProperties paymentsProperties) {
        this.paymentsProperties = paymentsProperties;
    }

    @Bean
    public RestTemplate paymentsClient(RestTemplateResponseErrorHandler errorHandler) {
        RestTemplateProperties properties = paymentsProperties.getPaymentsProperties();

        return new RestTemplateBuilder()
                .rootUri(properties.getUri())
                .setConnectTimeout(properties.getConnectTimeout())
                .setReadTimeout(properties.getReadTimeout())
                .errorHandler(errorHandler)
                .build();

//                return new RestTemplateBuilder()
//                .rootUri("http://localhost:8989")
//                .setConnectTimeout(Duration.ofSeconds(5))
//                .setReadTimeout(Duration.ofSeconds(5))
//                .build();
    }
}
