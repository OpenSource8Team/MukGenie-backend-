package org.example.mukgenie.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/arff")
    public void exportToArff() {
        foodService.exportToArff();
    }

    @GetMapping("/result")
    public String getResult(
            @RequestParam String category,
            @RequestParam String ingredient,
            @RequestParam int temperature,
            @RequestParam boolean spiciness,
            @RequestParam boolean broth,
            @RequestParam boolean oiliness,
            @RequestParam String cookingType) {
        return foodService.classify(category, ingredient, temperature, spiciness, broth, oiliness, cookingType);
    }
}
