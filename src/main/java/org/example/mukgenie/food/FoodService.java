package org.example.mukgenie.food;

import org.example.mukgenie.decision_tree.MainQuestion;

import java.util.List;

public interface FoodService {
    // 새로운 음식 생성
    Food createFood(Food food);

    // 모든 음식 조회
    List<Food> getAllFoods();

    // 특정 ID를 가진 음식 조회
    Food getFoodById(String id);

    // 특정 ID를 가진 음식 삭제
    void deleteFoodById(String id);

    // 설문 결과
    String returnSurveyResult(MainQuestion surveyResult);



    void exportToArff(String fileName, String directoryPath);
}
