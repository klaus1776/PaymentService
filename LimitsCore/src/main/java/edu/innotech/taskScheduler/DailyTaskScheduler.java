package edu.innotech.taskScheduler;

import edu.innotech.service.LimitsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyTaskScheduler {
    private final LimitsService limitsService;

    public DailyTaskScheduler(LimitsService limitsService) {
        this.limitsService = limitsService;
    }

    // Метод, обновляющий лимиты пользователей в 00:00:00
    @Scheduled(cron = "0 0 0 * * ?")
    public void executeUpdateUsersLimitsTask() {
        limitsService.setLimitValue();
    }
}
