package org.example.mukgenie.decision_tree;

import java.util.HashMap;
import java.util.Map;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;

public class MainQuestion {

    private static final Map<String, String> countryMap = new HashMap<>();
    private static final Map<String, String> ingredientMap = new HashMap<>();
    private static final Map<String, Integer> temperatureMap = new HashMap<>();
    private static final Map<String, Boolean> spicinessMap = new HashMap<>();
    private static final Map<String, Boolean> soupMap = new HashMap<>();
    private static final Map<String, Boolean> greasinessMap = new HashMap<>();
    private static final Map<String, String> cookingTypeMap = new HashMap<>();

    static {
        countryMap.put("A1", "한식");
        countryMap.put("A2", "일식");
        countryMap.put("A3", "중식");
        countryMap.put("A4", "양식");
        countryMap.put("A5", "기타");

        ingredientMap.put("A6", "육류");
        ingredientMap.put("A7", "채소류");
        ingredientMap.put("A8", "곡류");

        temperatureMap.put("A9", 1);
        temperatureMap.put("A10", 2);
        temperatureMap.put("A11", 3);

        spicinessMap.put("A12", true);
        spicinessMap.put("A13", false);

        soupMap.put("A14", true);
        soupMap.put("A15", false);

        greasinessMap.put("A16", true);
        greasinessMap.put("A17", false);

        cookingTypeMap.put("A18", "끓이기");
        cookingTypeMap.put("A19", "볶기");
        cookingTypeMap.put("A20", "비조리");
        cookingTypeMap.put("A21", "삶기");
        cookingTypeMap.put("A22", "튀기기");
        cookingTypeMap.put("A23", "굽기");
    }

    public static String predictFood(String answer, String arffFilePath) throws Exception {
        Instances data = createInstance(answer);

        // ARFF 파일 로드
        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(arffFilePath));
        Instances loadedData = loader.getDataSet();
        loadedData.setClassIndex(loadedData.numAttributes() - 1); // 클래스 인덱스 설정

        // 의사결정트리 생성 및 훈련
        J48 tree = new J48();
        tree.buildClassifier(loadedData);

        // 필터링 및 예측
        Instances filteredData = dataCollector(data, tree);
        return filteredData.firstInstance().stringValue(filteredData.numAttributes() - 1);
    }

    private static Instances createInstance(String answer) {
        Instances instances = new Instances("food_instance", createAttributes(), 1);
        DenseInstance denseInstance = new DenseInstance(7);
        denseInstance.setDataset(instances);

        switch (answer) {
            case "A1", "A2", "A3", "A4", "A5" -> denseInstance.setValue(instances.attribute(0), countryMap.get(answer));
            case "A6", "A7", "A8" -> denseInstance.setValue(instances.attribute(1), ingredientMap.get(answer));
            case "A9", "A10", "A11" -> denseInstance.setValue(instances.attribute(2), temperatureMap.get(answer));
            case "A12", "A13" -> denseInstance.setValue(instances.attribute(3), spicinessMap.get(answer) ? 0 : 1);
            case "A14", "A15" -> denseInstance.setValue(instances.attribute(4), soupMap.get(answer) ? 0 : 1);
            case "A16", "A17" -> denseInstance.setValue(instances.attribute(5), greasinessMap.get(answer) ? 0 : 1);
            case "A18", "A19", "A20", "A21", "A22", "A23" -> denseInstance.setValue(instances.attribute(6), cookingTypeMap.get(answer));
            default -> {}
        }

        instances.add(denseInstance);
        return instances;
    }

    private static Instances dataCollector(Instances data, J48 tree) throws Exception {
        DenseInstance newInstance = new DenseInstance(data.numAttributes());
        newInstance.setDataset(data);

        for (int i = 0; i < data.numAttributes() - 1; i++) {
            newInstance.setValue(data.attribute(i), data.get(0).value(i));
        }

        // 예측 수행
        double result = tree.classifyInstance(newInstance);
        String predictedClass = data.classAttribute().value((int) result);
        System.out.println("Predicted class: " + predictedClass); // 예측 결과 출력 (코드 실행 시 출력)

        Instances filteredData = new Instances(data, 0);
        filteredData.add(newInstance);
        return filteredData;
    }

    private static java.util.ArrayList<weka.core.Attribute> createAttributes() {
        java.util.ArrayList<weka.core.Attribute> attributes = new java.util.ArrayList<>(7);
        attributes.add(new weka.core.Attribute("country"));
        attributes.add(new weka.core.Attribute("ingredient"));
        attributes.add(new weka.core.Attribute("temperature"));
        attributes.add(new weka.core.Attribute("spiciness"));
        attributes.add(new weka.core.Attribute("soup"));
        attributes.add(new weka.core.Attribute("greasiness"));
        attributes.add(new weka.core.Attribute("cooking_type"));
        return attributes;
    }
}
