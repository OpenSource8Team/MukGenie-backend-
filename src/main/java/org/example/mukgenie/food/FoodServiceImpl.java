package org.example.mukgenie.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    @Override
    public void exportToArff() {
        String fileName = "FoodChoice.arff";
        String directoryPath = "src/main/resources";

        List<Food> foods = foodRepository.findAll();
        Path filePath = Path.of(directoryPath, fileName);
        try {
            Instances data = createArffInstances(foods);
            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(new File(filePath.toString()));
            saver.writeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Instances createArffInstances(List<Food> foods) {
        // 속성 정의
        Attribute country = new Attribute("나라", List.of("한식", "일식", "중식", "양식", "기타"));
        Attribute ingredient = new Attribute("재료", List.of("육류", "채소류", "곡류"));
        Attribute temperature = new Attribute("온도", List.of("1", "2", "3"));
        Attribute spiciness = new Attribute("맵기", List.of("true", "false"));
        Attribute broth = new Attribute("국물", List.of("true", "false"));
        Attribute oiliness = new Attribute("기름기", List.of("true", "false"));
        Attribute cookingType = new Attribute("조리타입", List.of("끓이기", "볶기", "비조리", "삶기", "튀기기", "굽기"));
        Attribute name = new Attribute("이름", (List<String>) null);

        // 속성 목록 생성
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(country);
        attributes.add(ingredient);
        attributes.add(temperature);
        attributes.add(spiciness);
        attributes.add(broth);
        attributes.add(oiliness);
        attributes.add(cookingType);
        attributes.add(name);

        // 데이터셋 생성
        Instances data = new Instances("FoodChoice", attributes, 0);
        data.setClassIndex(attributes.size() - 1);

        // 데이터 추가
        for (Food food : foods) {
            if (food.getName() == null) { // 이름이 null인 경우 건너뜀
                continue;
            }
            DenseInstance instance = new DenseInstance(attributes.size());
            instance.setValue(country, food.getCategory());
            instance.setValue(ingredient, food.getIngredient());
            instance.setValue(temperature, food.getTemperature().toString());
            instance.setValue(spiciness, food.getSpiciness().toString());
            instance.setValue(broth, food.getBroth().toString());
            instance.setValue(oiliness, food.getOiliness().toString());
            instance.setValue(cookingType, food.getCookingType());
            instance.setValue(name, food.getName());
            data.add(instance);
        }

        return data;
    }

}