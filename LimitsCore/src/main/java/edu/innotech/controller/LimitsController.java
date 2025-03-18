package edu.innotech.controller;

import edu.innotech.dto.UsersLimitDto;
import edu.innotech.service.LimitsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/payments")
public class LimitsController {
    private final LimitsService limitsService;

    public LimitsController(LimitsService limitsService) {
        this.limitsService = limitsService;
    }

    @GetMapping("/limit")
    public UsersLimitDto checkUserLimit(@RequestParam("userId") Long userId) {
        var response = limitsService.checkUserLimit(userId);
        return response;
    }

    @PostMapping(value = "/limitupdate")
    public void updateLimitValue(@RequestParam("userId") Long userId, @RequestParam("limit") Double limit) {
        limitsService.updateLimitValue(userId, limit);
    }

    // Метод, уменьшающий лимит клиента
    @PostMapping(value = "/limitdec")
    public void decreaseLimitValue(@RequestParam("userId") Long userId, @RequestParam("dec") Double decrement) {
        limitsService.decreaseLimitValue(userId, decrement);
    }

    // Метод, восстанавливающий лимит клиента
    @PostMapping(value = "/limitrec")
    public void recoveryLimitValue(@RequestParam("userId") Long userId, @RequestParam("rec") Double recovery) {
        limitsService.recoveryLimitValue(userId, recovery);
    }
}
