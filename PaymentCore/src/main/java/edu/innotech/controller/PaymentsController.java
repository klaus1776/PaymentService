package edu.innotech.controller;

import edu.innotech.dto.PaymentsResponceDto;
import edu.innotech.service.PaymentsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/products")
public class PaymentsController {
    private final PaymentsService paymentService;

    public PaymentsController(PaymentsService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/product")
    public PaymentsResponceDto getProducttById(@RequestParam("id") Long productId) {
        var response = paymentService.getProducttById(productId);
        return response;
    }

    @GetMapping("/account")
    public PaymentsResponceDto getProducttByAccount(@RequestParam("num") String accountNum) {
        var response = paymentService.getProducttByAccount(accountNum);
        return response;
    }

    @GetMapping("/user")
    public PaymentsResponceDto findProductsByUserId(@RequestParam("id") Long userId) {
        var response = paymentService.findProductsByUserId(userId);
        return response;
    }

    @PostMapping(value = "/payment")
    //@ResponseStatus(HttpStatus.OK)
    public void makePayment(@RequestParam("userId") Long userId, @RequestParam("accNum") String accountNum, @RequestParam("sum") Double summa) {
        paymentService.makePayment(userId, accountNum, summa);
    }
}