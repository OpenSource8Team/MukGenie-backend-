package org.example.mukgenie.food;

import org.example.mukgenie.food.Food;

import java.util.List;

public interface FoodService {
    // 새로운 사용자 생성
    Food createFood(Food food);

    // 모든 사용자 조회
    List<Food> getAllFoods();

    // 특정 ID를 가진 사용자 조회
    Food getFoodById(String id);

    // 특정 ID를 가진 사용자 삭제
    void deleteFoodById(String id);
}
