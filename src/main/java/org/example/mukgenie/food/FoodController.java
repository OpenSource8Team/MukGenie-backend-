package org.example.mukgenie.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// 사용자 관련 HTTP 요청을 처리하는 컨트롤러
@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // 새로운 음식 생성
    @PostMapping
    public Food createFood(@RequestBody Food food) {
        return foodService.createFood(food);
    }

    // 모든 음식 조회
    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    // 특정 ID를 가진 음식 조회
    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable String id) {
        return foodService.getFoodById(id);
    }

    // 특정 ID를 가진 음식 삭제
    @DeleteMapping("/{id}")
    public void deleteFoodById(@PathVariable String id) {
        foodService.deleteFoodById(id);
    }
}
