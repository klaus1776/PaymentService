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
}
