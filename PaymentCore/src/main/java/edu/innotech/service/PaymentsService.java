package edu.innotech.service;

import edu.innotech.dto.PaymentsResponceDto;
import edu.innotech.dto.UsersLimitDto;
import edu.innotech.exception.IntegrationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentsService {
    private final RestTemplate paymentsClient;
    private final RestTemplate limitsClient;
    private final String paymentProducts;
    private final String limitProducts;

    public PaymentsService(//@Qualifier("paymentsClient")
                           RestTemplate paymentsClient,
                           //@Qualifier("limitsClient")
                           RestTemplate limitsClient,
                           @Value("${service.payment-client-products}") String paymentProducts,
                           @Value("${service.limit-client-products}") String limitProducts) {
        this.paymentsClient = paymentsClient;
        this.limitsClient = limitsClient;
        this.paymentProducts = paymentProducts;
        this.limitProducts = limitProducts;
    }

    // Метод, получающий продукт по id
    public PaymentsResponceDto getProducttById(Long productId) {
        Map<String, String> params = Collections.singletonMap("id", String.valueOf(productId));
        return paymentsClient.getForObject(
                paymentProducts + "/product?id={id}",
                PaymentsResponceDto.class,
                params
                );
    }

    // Метод, получающий продукт по номеру счета
    public PaymentsResponceDto getProducttByAccount(String accountNum) {
        Map<String, String> params = Collections.singletonMap("accountNum", accountNum);
        return paymentsClient.getForObject(
                paymentProducts + "/account?num={accountNum}",
                PaymentsResponceDto.class,
                params
        );
    }

    // Метод, получающий все продукты клиента по id клиента (для выбора продукта при исполнении платежа)
    public PaymentsResponceDto findProductsByUserId(Long userId) {
        Map<String, String> params = Collections.singletonMap("userId", String.valueOf(userId));
        return paymentsClient.getForObject(
                paymentProducts + "/user?id={userId}",
                PaymentsResponceDto.class,
                params
        );
    }

    // Метод, проверяющий наличие лимита у клиента по id клиента через сервил лимитов LimitsCore
    public UsersLimitDto checkUserLimit(Long userId) {
        Map<String, String> params = Collections.singletonMap("userId", String.valueOf(userId));
        return limitsClient.getForObject(
                limitProducts + "/limit?userId={userId}",
                UsersLimitDto.class,
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
        UsersLimitDto limit = checkUserLimit(userId);
        // Проверка лимита на списание средств со счета продукта
        if (limit.usersLimit().getLimitValue() < summa) {
            throw new IntegrationException("The limit on spending funds from the account has been exceeded", "402 PAYMENT_REQUIRED");
        }

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(products.products().get(0).getId()));
        params.put("sum", String.valueOf(products.products().get(0).getAmount() - summa));
        paymentsClient.postForObject(
                paymentProducts + "/payment?id={id}&amount={sum}",
                null,
                PaymentsResponceDto.class,
                params
        );

        // Обновление значения лимита с учетом потраченных клиентом средств
        params.clear();
        params.put("userId", String.valueOf(limit.usersLimit().getUserId()));
        params.put("limit", String.valueOf(limit.usersLimit().getLimitValue() - summa));
        limitsClient.postForObject(
                limitProducts + "/limitupdate?userId={userId}&limit={limit}",
                null,
                UsersLimitDto.class,
                params
        );
    }

}
