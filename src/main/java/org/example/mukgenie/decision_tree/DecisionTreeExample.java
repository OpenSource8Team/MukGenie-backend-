package org.example.mukgenie.decision_tree;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;

public class DecisionTreeExample {

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

            // 의사결정트리 평가
            Evaluation eval = new Evaluation(data); // 데이터 평가를 위한 Evaluation 객체 생성
            eval.evaluateModel(tree, data); // 의사결정트리 모델을 이용하여 데이터 평가
            System.out.println(eval.toSummaryString()); // 평가 결과 출력

            // 새로운 입력값으로 예측 수행
            double[] values = new double[data.numAttributes()];
            values[0] = data.attribute(0).indexOfValue("일본"); // 첫 번째 속성: 이탈리아
            values[1] = data.attribute(1).indexOfValue("회"); // 두 번째 속성: 피자
            values[2] = data.attribute(2).indexOfValue("매운맛"); // 세 번째 속성: 안매운맛
            values[3] = data.attribute(3).indexOfValue("보통"); // 네 번째 속성: 보통
            values[4] = data.attribute(4).indexOfValue("아침"); // 다섯 번째 속성: 아침

            // 입력값으로 Instance 객체 생성
            DenseInstance newInstance = new DenseInstance(1.0, values);
            newInstance.setDataset(data);

            // 예측 수행
            double result = tree.classifyInstance(newInstance);
            String predictedClass = data.classAttribute().value((int) result);
            System.out.println("Predicted class: " + predictedClass); // 예측 결과 출력

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
