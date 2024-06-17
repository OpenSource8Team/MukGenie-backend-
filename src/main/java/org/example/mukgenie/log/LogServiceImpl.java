package org.example.mukgenie.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 로그 서비스 구현 클래스
@Service
public class LogServiceImpl implements LogService {
    private static final int MAX_FOODS_SIZE = 20;  // 음식 목록의 최대 크기
    private final LogRepository logRepository;  // 로그 리포지토리

    // @Autowired: Spring이 자동으로 필요한 의존성을 주입합니다.
    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    // 특정 사용자 ID로 음식 목록을 조회하는 메서드
    @Override
    public List<String> getFoodsByUserId(String userId) {
        Log log = logRepository.findByUserId(userId);
        if (log != null) {
            return log.getFoods();
        } else {
            return Collections.emptyList();  // 로그가 없을 경우 빈 목록 반환
        }
    }

    // 특정 사용자 ID와 음식 이름으로 로그에서 항목을 삭제하는 메서드
    @Override
    public void deleteFoodByName(String userId, String foodName) {
        Log log = logRepository.findByUserId(userId);
        if (log != null && log.getFoods() != null) {
            log.getFoods().remove(foodName);
            logRepository.save(log);  // 변경된 로그 저장
        }
    }

    // 특정 사용자 ID와 음식 이름으로 로그에 항목을 추가하는 메서드
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

        foods.add(foodName);  // 새로운 음식 항목 추가
        logRepository.save(log);  // 변경된 로그 저장
    }
}
