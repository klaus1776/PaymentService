package edu.innotech.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.innotech.dto.ErrorExternalResponseDto;
import edu.innotech.exception.IntegrationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError()
                || response.getStatusCode().is5xxServerError();
    };

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorExternalResponseDto responseDto = objectMapper.readValue(response.getBody(), ErrorExternalResponseDto.class);
            throw new IntegrationException("Ошибка при обращении к внешней API: " + responseDto.getMessage(), String.valueOf(response.getStatusCode()));
        }
    }

}
