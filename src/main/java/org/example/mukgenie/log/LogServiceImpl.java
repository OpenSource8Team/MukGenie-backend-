package org.example.mukgenie.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    private static final int MAX_FOODS_SIZE = 20;
    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<String> getFoodsByUserId(String userId) {
        Log log = logRepository.findByUserId(userId);
        if (log != null) {
            return log.getFoods();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteFoodByName(String userId, String foodName) {
        Log log = logRepository.findByUserId(userId);
        if (log != null && log.getFoods() != null) {
            log.getFoods().remove(foodName);
            logRepository.save(log);
        }
    }

    @Override
    public void addFood(String userId, String foodName) {
        Log log = logRepository.findByUserId(userId);
        if (log == null) {
            log = new Log();
            log.setUserId(userId);
            log.setFoods(new ArrayList<>());
        }
        if (log.getFoods() == null) {
            log.setFoods(new ArrayList<>());
        }
        List<String> foods = log.getFoods();

        // 배열 크기가 MAX_FOODS_SIZE를 초과하면 오래된 항목을 제거
        if (foods.size() >= MAX_FOODS_SIZE) {
            foods.remove(0);  // 첫 번째 항목 제거 (오래된 항목)
        }

        foods.add(foodName);
        logRepository.save(log);
    }
}
