package org.example.mukgenie.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public Food getFoodById(String id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFoodById(String id) {
        foodRepository.deleteById(id);
    }

    @Override
    public void exportToArff(String fileName, String directoryPath) {
        List<Food> foods = foodRepository.findAll();
        Path filePath = Path.of(directoryPath, fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            // ARFF 파일 헤더 작성
            writer.write("@relation FoodChoice\n\n");
            writer.write("@attribute 나라 {한식, 일식, 중식, 양식, 기타}\n");
            writer.write("@attribute 재료 {육류, 채소류, 곡류}\n");
            writer.write("@attribute 온도 {1, 2, 3}\n");
            writer.write("@attribute 맵기 {true, false}\n");
            writer.write("@attribute 국물 {true, false}\n");
            writer.write("@attribute 기름기 {true, false}\n");
            writer.write("@attribute 조리타입 {끓이기, 볶기, 비조리, 삶기, 튀기기, 굽기}\n");
            writer.write("@attribute 이름 {");

            // 음식 이름 목록 작성
            List<String> foodNames = foods.stream()
                    .filter(food -> food.getName() != null) // 이름이 null이 아닌 것만 필터링
                    .map(Food::getName)
                    .toList();
            for (int i = 0; i < foodNames.size(); i++) {
                writer.write(foodNames.get(i));
                if (i < foodNames.size() - 1) {
                    writer.write(", ");
                }
            }

            writer.write("}\n\n@data\n");

            // 데이터 작성
            for (Food food : foods) {
                if (food.getName() == null) { // 이름이 null인 경우 건너뜀
                    continue;
                }
                writer.write(food.getCategory() + ", " + food.getIngredient() + ", " +
                        food.getTemperature() + ", " + food.getSpiciness() + ", " +
                        food.getBroth() + ", " + food.getOiliness() + ", " +
                        food.getCookingType() + ", " + food.getName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
