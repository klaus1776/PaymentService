package edu.innotech.service;

import edu.innotech.dto.UsersLimitDto;
import edu.innotech.entity.UsersLimit;
import edu.innotech.exceptions.NoDataFoundException;
import edu.innotech.repository.LimitsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LimitsService {
    private final LimitsRepository limitsRepository;
    private final String limitsProducts;
    private final String limitsDateFormat;
    private final String limitsStartDate;
    private final Double limitsValue;

    // Метод, обновляющий лимиты пользователей в 00:00:00
    private void updateUserLimits(String dateFormat, String startDate) {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            calendar.setTime(sdf.parse(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Запуск потока обновляющего лимиты пользователей в 00:00:00
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               // Обновление лимитов пользователей в 00:00:00
                               limitsRepository.setLimitValue();
                           }
                       }, calendar.getTime(), 24 * 60 * 60 * 1000
        );
    }

    public LimitsService(LimitsRepository limitsRepository,
                         //RestTemplate restTemplate,
                         @Value("${service.limits-client-products}") String limitsProducts,
                         @Value("${service.limits-start-date-format}") String limitsDateFormat,
                         @Value("${service.limits-start-date}") String limitsStartDate,
                         @Value("${service.limits-value}") Double limitsValue) {
        this.limitsRepository = limitsRepository;
        this.limitsProducts = limitsProducts;
        this.limitsDateFormat = limitsDateFormat;
        this.limitsStartDate = limitsStartDate;
        this.limitsValue = limitsValue;

        updateUserLimits(limitsDateFormat, limitsStartDate);
    }

    public UsersLimitDto findLimitByUserId(Long userId) {
        UsersLimit usersLimit = limitsRepository.findLimitByUserId(userId).
                orElseThrow(() -> new NoDataFoundException("Products not found", "NOT_FOUND"));
        return new UsersLimitDto(usersLimit);
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
}
