package edu.innotech.service;

import edu.innotech.dto.PaymentsResponceDto;
import edu.innotech.exception.IntegrationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentsService {
    private final RestTemplate restTemplate;
    private final String paymentProducts;

    public PaymentsService(RestTemplate restTemplate,
                           @Value("${service.payment-client-products}") String paymentProducts) {
        this.restTemplate = restTemplate;
        this.paymentProducts = paymentProducts;
    }

    // Метод, получающий продукт по id
    public PaymentsResponceDto getProducttById(Long productId) {
        Map<String, String> params = Collections.singletonMap("id", String.valueOf(productId));
        return restTemplate.getForObject(
                paymentProducts + "/product?id={id}",
                PaymentsResponceDto.class,
                params
                );
    }

    // Метод, получающий продукт по номеру счета
    public PaymentsResponceDto getProducttByAccount(String accountNum) {
        Map<String, String> params = Collections.singletonMap("accountNum", accountNum);
        return restTemplate.getForObject(
                paymentProducts + "/account?num={accountNum}",
                PaymentsResponceDto.class,
                params
        );
    }

    // Метод, получающий все продукты клиента по id клиента (для выбора продукта при исполнении платежа)
    public PaymentsResponceDto findProductsByUserId(Long userId) {
        Map<String, String> params = Collections.singletonMap("userId", String.valueOf(userId));
        return restTemplate.getForObject(
                paymentProducts + "/user?id={userId}",
                PaymentsResponceDto.class,
                params
        );
    }

    // Метод, исполняющий платеж с проверкой существования продукта и достаточности средств на нем
    public void makePayment(Long userId, String accountNum, Double summa) {
        // Поиск продукта в БД (проверка существования продукта)
        PaymentsResponceDto products = getProducttByAccount(accountNum);
        // Проверка достаточности средств на счете продукта
        if (products.products().get(0).getAmount() < summa) {
            throw new IntegrationException("Insufficient funds in the account", "402 PAYMENT_REQUIRED");
        }

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(products.products().get(0).getId()));
        params.put("sum", String.valueOf(products.products().get(0).getAmount() - summa));
        restTemplate.postForObject(
                paymentProducts + "/payment?id={id}&amount={sum}",
                null,
                PaymentsResponceDto.class,
                params
        );
    }

}
