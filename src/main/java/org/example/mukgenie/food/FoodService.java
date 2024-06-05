package org.example.mukgenie.food;

public interface FoodService {

    // ARFF 파일로 음식 데이터 내보내기
    void exportToArff();

    // 새로운 입력값으로 예측 수행
    String classify(String category, String ingredient, int temperature, boolean spiciness, boolean broth, boolean oiliness, String cookingType);
}
