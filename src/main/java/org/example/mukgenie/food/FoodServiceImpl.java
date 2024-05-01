package org.example.mukgenie.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// 사용자 관리 서비스 구현 클래스
@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    // 새로운 사용자 생성
    @Override
    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    // 모든 사용자 조회
    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    // 특정 ID를 가진 사용자 조회
    @Override
    public Food getFoodById(String id) {
        return foodRepository.findById(id).orElse(null);
    }

    // 특정 ID를 가진 사용자 삭제
    @Override
    public void deleteFoodById(String id) {
        foodRepository.deleteById(id);
    }
}
