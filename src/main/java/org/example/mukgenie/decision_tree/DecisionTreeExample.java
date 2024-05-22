package org.example.mukgenie.decision_tree;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
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

            // 의사결정트리 출력
            System.out.println(tree);

            // 의사결정트리 평가
            Evaluation eval = new Evaluation(data); // 데이터 평가를 위한 Evaluation 객체 생성
            eval.evaluateModel(tree, data); // 의사결정트리 모델을 이용하여 데이터 평가
            System.out.println(eval.toSummaryString()); // 평가 결과 출력

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
