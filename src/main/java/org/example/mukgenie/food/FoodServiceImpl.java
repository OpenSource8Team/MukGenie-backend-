package org.example.mukgenie.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private J48 tree;
    private Instances data;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
        initializeDecisionTree();
    }

    private void initializeDecisionTree() {
        try {
            // ARFF 파일 로드
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File("src/main/resources/FoodChoiceModified.arff")); // ARFF 파일 경로 설정
            data = loader.getDataSet(); // 데이터셋 로드
            data.setClassIndex(data.numAttributes() - 1); // 클래스 인덱스 설정

            // 의사결정트리 생성 및 훈련
            tree = new J48(); // J48 의사결정트리 객체 생성
            tree.buildClassifier(data); // 데이터를 이용하여 의사결정트리 훈련
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportToArff() {
        createArffFile("FoodChoice.arff");
        createArffFile("FoodChoiceModified.arff");
    }

    private void createArffFile(String fileName) {
        String directoryPath = "src/main/resources";

        List<Food> foods = foodRepository.findAll();
        Path filePath = Path.of(directoryPath, fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            // ARFF 파일 헤더 작성
            writer.write("@relation FoodChoice\n\n");
            writer.write("@attribute 나라 {한식, 일식, 중식, 양식, 기타}\n");
            writer.write("@attribute 재료 {육류, 채소류, 곡류, 수산류}\n");
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

    @Override
    public String classify(String category, String ingredient, int temperature, boolean spiciness, boolean broth, boolean oiliness, String cookingType) {
        try {
            double[] values = new double[data.numAttributes()];
            values[0] = data.attribute(0).indexOfValue(category);
            values[1] = data.attribute(1).indexOfValue(ingredient);
            values[2] = data.attribute(2).indexOfValue(String.valueOf(temperature));
            values[3] = data.attribute(3).indexOfValue(String.valueOf(spiciness));
            values[4] = data.attribute(4).indexOfValue(String.valueOf(broth));
            values[5] = data.attribute(5).indexOfValue(String.valueOf(oiliness));
            values[6] = data.attribute(6).indexOfValue(cookingType);

            DenseInstance newInstance = new DenseInstance(1.0, values);
            newInstance.setDataset(data);

            double result = tree.classifyInstance(newInstance);
            return data.classAttribute().value((int) result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
