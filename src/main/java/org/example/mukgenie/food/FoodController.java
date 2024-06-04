package org.example.mukgenie.food;

import org.example.mukgenie.decision_tree.MainQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/foods") // 엔드포인트를 "/foods"로 변경
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping // POST 요청을 받는 엔드포인트 설정
    public Food createFood(@RequestBody Food food) {
        return foodService.createFood(food);
    }

    @GetMapping // GET 요청을 받는 엔드포인트 설정
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}") // 경로 변수를 사용하여 특정 ID의 음식 조회 엔드포인트 설정
    public Food getFoodById(@PathVariable String id) {
        return foodService.getFoodById(id);
    }

    @DeleteMapping("/{id}") // DELETE 요청을 받는 엔드포인트 설정
    public void deleteFoodById(@PathVariable String id) {
        foodService.deleteFoodById(id);
    }

    //새로 추가하는 코드
    @PostMapping("/result") // POST 요청을 받는 엔드포인트 설정
    public void saveSurveyResult(@RequestBody MainQuestion surveyResult) {
        foodService.returnSurveyResult(surveyResult);
    }


}
