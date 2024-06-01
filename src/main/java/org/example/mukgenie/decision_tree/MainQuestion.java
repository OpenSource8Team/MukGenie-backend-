package org.example.mukgenie.decision_tree;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.util.Scanner;

public class MainQuestion {

    public static void main(String[] args) {
        try {
            // ARFF 파일 로드
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File("FoodChoice.arff")); // ARFF 파일 경로 설정
            Instances data = loader.getDataSet(); // 데이터셋 로드
            data.setClassIndex(data.numAttributes() - 1); // 클래스 인덱스 설정

            // 의사결정트리 생성 및 훈련
            J48 tree = new J48(); // J48 의사결정트리 객체 생성
            tree.buildClassifier(data); // 데이터를 이용하여 의사결정트리 훈련

            // 사용자 응답에 따라 음식 필터링
            Instances filteredData = filterFood(data, tree);

            // 여기서 filteredData의 클래스 속성 값을 설정합니다.
            //String finalResult = filteredData.firstInstance().stringValue(filteredData.numAttributes() - 1);

            // 올바르게 설정된 클래스 속성 값을 출력합니다.
            // System.out.println("최종 결과: " + finalResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     모든 나라(한,일,중,서) no 응답시 ---> @atrribute 나라 {기타}
     따뜻한 음식 , 차가운 음식 질문 둘다 no응답 --> {온도2 속성값}

     질문 타입들 각각 긍정 응답 질문페이지가 나오면 바로 다음 질문타입 유형으로 넘어감
     */
    // 사용자 응답에 따라 음식 필터링
    public static Instances filterFood(Instances data, J48 tree) throws Exception {
        Scanner scanner = new Scanner(System.in);
        DenseInstance newInstance = new DenseInstance(data.numAttributes());
        newInstance.setDataset(data); // 데이터셋 설정 추가

        // 나라 카테고리 질문
        // System.out.println("나라 카테고리 질문을 시작합니다.");
        String countryValue = askQuestionAndGetValue(scanner, "나라");
        newInstance.setValue(data.attribute(0), countryValue);

        // 재료 카테고리 질문
        //System.out.println("재료 카테고리 질문을 시작합니다.");
        String ingredientValue = askQuestionAndGetValue(scanner, "재료");
        newInstance.setValue(data.attribute(1), ingredientValue);

        // 온도 카테고리 질문
        //System.out.println("온도 카테고리 질문을 시작합니다.");
        int temperatureIndex = askTemperatureQuestionAndGetIndex(scanner, "온도");
        newInstance.setValue(data.attribute(2), data.attribute(2).value(temperatureIndex));

        // 맵기 카테고리 질문
        boolean spiciness = askSpicinessQuestion(scanner, "맵기");
        newInstance.setValue(data.attribute(3), spiciness ? 0 : 1); // 0: true, 1: false

        // 국물 카테고리 질문
        boolean soup = askSoupQuestion(scanner, "국물");
        newInstance.setValue(data.attribute(4), soup ? 0 : 1); // 0: true, 1: false

        // 기름기 카테고리 질문
        boolean greasiness = askGreasinessQuestion(scanner, "기름기");
        newInstance.setValue(data.attribute(5), greasiness ? 0 : 1); // 0: true, 1: false

        // 조리타입 카테고리 질문
        //System.out.println("조리타입 카테고리 질문을 시작합니다.");
        String cookingType = askCookingTypeQuestionAndGetAnswer(scanner, "조리타입");
        newInstance.setValue(data.attribute(6), cookingType);

        // 각 질문에 해당하는 값 출력
        System.out.println("나라 질문에 대한 답변: " + countryValue);
        System.out.println("재료 질문에 대한 답변: " + ingredientValue);
        System.out.println("온도 질문에 대한 답변: " + data.attribute(2).value(temperatureIndex));
        System.out.println("맵기 질문에 대한 답변: " + (spiciness ? "true" : "false"));
        System.out.println("국물 질문에 대한 답변: " + (soup ? "true" : "false"));
        System.out.println("기름기 질문에 대한 답변: " + (greasiness ? "true" : "false"));
        System.out.println("조리타입 질문에 대한 답변: " + cookingType);

        // 예측 수행
        double result = tree.classifyInstance(newInstance);
        String predictedClass = data.classAttribute().value((int) result);
        System.out.println("Predicted class: " + predictedClass); // 예측 결과 출력

        // newInstance를 Instances로 변환하여 반환
        Instances filteredData = new Instances(data, 0);
        filteredData.add(newInstance);

        return filteredData;
    }


    // 사용자에게 질문을 하고 대답에 따른 문자열을 반환하는 메서드
    private static String askQuestionAndGetValue(Scanner scanner, String categoryName) {
        System.out.println(categoryName + " 카테고리 질문을 시작합니다.");
        String value = "";

        if (categoryName.equals("나라")) {
            System.out.println("한식을 먹고싶어? (네/아니오): ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("네")) {
                value = "한식";
            } else {
                System.out.println("일식을 먹고싶어? (네/아니오): ");
                answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("네")) {
                    value = "일식";
                } else {
                    System.out.println("중식을 먹고싶어? (네/아니오): ");
                    answer = scanner.nextLine();
                    if (answer.equalsIgnoreCase("네")) {
                        value = "중식";
                    } else {
                        System.out.println("양식을 먹고싶어? (네/아니오): ");
                        answer = scanner.nextLine();
                        if (answer.equalsIgnoreCase("네")) {
                            value = "양식";
                        } else {
                            value = "기타";
                        }
                    }
                }
            }
        } else if (categoryName.equals("재료")) {
            System.out.println("육류를 먹고싶어? (네/아니오): ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("네")) {
                value = "육류";
            } else {
                System.out.println("채소류를 먹고싶어? (네/아니오): ");
                answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("네")) {
                    value = "채소류";
                } else {
                    value = "곡류";
                }
            }
        }
        return value;
    }

    // 사용자에게 온도 질문을 하고 대답에 따른 인덱스를 반환하는 메서드
    private static int askTemperatureQuestionAndGetIndex(Scanner scanner, String categoryName) {
        System.out.println(categoryName + " 카테고리 질문을 시작합니다.");
        int temperatureIndex = -1; // 선택한 온도의 인덱스를 저장할 변수
        System.out.println("뜨거운 음식을 먹고싶어? (네/아니오): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("네")) {
            temperatureIndex = 0;
        } else {
            System.out.println("차가운 음식을 먹고 싶어? (네/아니오): ");
            answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("네")) {
                temperatureIndex = 2;
            } else {
                temperatureIndex = 1;
            }
        }
        return temperatureIndex;
    }

    // 사용자에게 매운음식 질문을 하고 대답에 따른 참/거짓 값을 반환하는 메서드
    private static boolean askSpicinessQuestion(Scanner scanner, String categoryName) {
        System.out.println(categoryName + " 카테고리 질문을 시작합니다.");
        boolean spiciness = false; // 선택한 맵기를 저장할 변수
        System.out.println("매운 음식을 먹고싶어? (네/아니오): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("네")) {
            spiciness = true;
        }
        return spiciness;
    }

    // 사용자에게 국물 질문을 하고 대답에 따른 참/거짓 값을 반환하는 메서드
    private static boolean askSoupQuestion(Scanner scanner, String categoryName) {
        System.out.println(categoryName + " 카테고리 질문을 시작합니다.");
        boolean soup = false; // 선택한 국물을 저장할 변수
        System.out.println("국물 있는 음식을 먹고싶어? (네/아니오): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("네")) {
            soup = true;
        }
        return soup;
    }

    // 사용자에게 카테고리 질문을 하고 true 또는 false 값을 반환하는 메서드
    private static boolean askGreasinessQuestion(Scanner scanner, String categoryName) {
        System.out.println(categoryName + " 카테고리 질문을 시작합니다.");
        boolean result = false; // 선택한 카테고리의 값
        System.out.println(categoryName + " 있는 음식을 먹고싶어? (네/아니오): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("네")) {
            result = true;
        }
        return result;
    }

    // 사용자에게 조리타입 질문을 하고 대답에 따른 문자열을 반환하는 메서드
    private static String askCookingTypeQuestionAndGetAnswer(Scanner scanner, String categoryName) {
        System.out.println(categoryName + " 카테고리 질문을 시작합니다.");
        String cookingType = ""; // 선택한 조리타입을 저장할 변수
        System.out.println("끓이기를 하고싶어? (네/아니오): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("네")) {
            cookingType = "끓이기"; // 끓이기 선택
        } else {
            System.out.println("볶기를 하고싶어? (네/아니오): ");
            answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("네")) {
                cookingType = "볶기"; // 볶기 선택
            } else {
                System.out.println("비조리를 하고싶어? (네/아니오): ");
                answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("네")) {
                    cookingType = "비조리"; // 비조리 선택
                } else {
                    System.out.println("삶기를 하고싶어? (네/아니오): ");
                    answer = scanner.nextLine();
                    if (answer.equalsIgnoreCase("네")) {
                        cookingType = "삶기"; // 삶기 선택
                    } else {
                        System.out.println("튀기기를 하고싶어? (네/아니오): ");
                        answer = scanner.nextLine();
                        if (answer.equalsIgnoreCase("네")) {
                            cookingType = "튀기기"; // 튀기기 선택
                        } else {
                            cookingType = "굽기"; // 굽기 선택
                        }
                    }
                }
            }
        }
        return cookingType;
    }
}