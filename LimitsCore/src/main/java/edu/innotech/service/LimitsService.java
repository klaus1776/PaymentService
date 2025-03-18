package edu.innotech.service;

import edu.innotech.dto.UserLimitMapper;
import edu.innotech.dto.UsersLimitDto;
import edu.innotech.entity.UsersLimit;
import edu.innotech.exceptions.NoDataFoundException;
import edu.innotech.repository.LimitsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LimitsService {
    private final LimitsRepository limitsRepository;
    private final Double limitsValue;

    public LimitsService(LimitsRepository limitsRepository,
                         @Value("${service.limits-value}") Double limitsValue) {
        this.limitsRepository = limitsRepository;
        this.limitsValue = limitsValue;
    }

    // Метод, обновляющий лимиты пользователей
    public void setLimitValue() {
        limitsRepository.setLimitValue();
    };

    public UsersLimitDto findLimitByUserId(Long userId) {
        UserLimitMapper mapper = new UserLimitMapper();
        UsersLimit usersLimit = limitsRepository.findLimitByUserId(userId).
                orElseThrow(() -> new NoDataFoundException("Products not found", "NOT_FOUND"));
        return mapper.map(usersLimit, UsersLimitDto.class);
    }

    @Transactional
    public UsersLimitDto checkUserLimit(Long userId) {
        UsersLimitDto usersLimitDto;
        try {
            usersLimitDto = findLimitByUserId(userId);
        } catch (NoDataFoundException e) {
            limitsRepository.createUserLimit(userId, limitsValue);
            usersLimitDto = findLimitByUserId(userId);
        }
        return usersLimitDto;
    }

    public void updateLimitValue(Long userId, Double limit) {
        limitsRepository.updateLimitValue(userId, limit);
    }

    // Метод, уменьшающий лимит клиента
    public void decreaseLimitValue(Long userId, Double decrement) {
        limitsRepository.decreaseLimitValue(userId, decrement);
    }

    // Метод, восстанавливающий лимит клиента
    public void recoveryLimitValue(Long userId, Double recovery) {
        limitsRepository.recoveryLimitValue(userId, recovery);
    }
}
