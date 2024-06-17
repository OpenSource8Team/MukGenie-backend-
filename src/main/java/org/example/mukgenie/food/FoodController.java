package org.example.mukgenie.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    // @Autowired: Spring이 자동으로 필요한 의존성을 주입합니다.
    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // /foods/arff 엔드포인트로 GET 요청이 오면 호출됩니다.
    // 데이터를 ARFF 형식으로 내보내는 메서드
    @GetMapping("/arff")
    public void exportToArff() {
        foodService.exportToArff();
    }

    // /foods/result 엔드포인트로 GET 요청이 오면 호출됩니다.
    // 음식 분류 결과를 반환하는 메서드
    @GetMapping("/result")
    public String getResult(
            @RequestParam String category,    // 요청 파라미터 'category'를 받습니다.
            @RequestParam String ingredient,  // 요청 파라미터 'ingredient'를 받습니다.
            @RequestParam int temperature,    // 요청 파라미터 'temperature'를 받습니다.
            @RequestParam boolean spiciness,  // 요청 파라미터 'spiciness'를 받습니다.
            @RequestParam boolean broth,      // 요청 파라미터 'broth'를 받습니다.
            @RequestParam boolean oiliness,   // 요청 파라미터 'oiliness'를 받습니다.
            @RequestParam String cookingType) // 요청 파라미터 'cookingType'을 받습니다.
    {
        // FoodService의 classify 메서드를 호출하여 결과를 반환합니다.
        return foodService.classify(category, ingredient, temperature, spiciness, broth, oiliness, cookingType);
    }
}
