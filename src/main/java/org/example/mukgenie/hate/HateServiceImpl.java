package org.example.mukgenie.hate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.*;

// Spring의 서비스로 등록되는 HateService 인터페이스의 구현체
@Service
public class HateServiceImpl implements HateService {

    // HateRepository 의존성 주입
    private final HateRepository hateRepository;

    // 생성자를 통한 의존성 주입
    @Autowired
    public HateServiceImpl(HateRepository hateRepository) {
        this.hateRepository = hateRepository;
    }

    // 새로운 Hate 생성 메서드 구현
    @Override
    public Hate createHate(Hate hate) {
        // HateRepository를 사용하여 Hate 객체를 저장하고 반환
        return hateRepository.save(hate);
    }

    // 모든 Hate 조회 메서드 구현
    @Override
    public List<Hate> getAllHates() {
        // HateRepository를 사용하여 모든 Hate 객체를 조회하고 반환
        return hateRepository.findAll();
    }

    // 특정 ID를 가진 Hate 조회 메서드 구현
    @Override
    public Hate getHateById(String id) {
        // HateRepository를 사용하여 특정 ID를 가진 Hate 객체를 조회하고 반환
        return hateRepository.findById(id).orElse(null);
    }

    // 특정 ID를 가진 Hate 삭제 메서드 구현
    @Override
    public void deleteHateById(String id) {
        // HateRepository를 사용하여 특정 ID를 가진 Hate 객체를 삭제
        hateRepository.deleteById(id);
    }
    // 주어진 알레르기 항목을 기반으로 ARFF 파일에서 해당 항목을 제거
    public void removeItemsFromARFF(List<String> allergies) throws Exception {
        // ARFF 파일 로드
        ArffLoader loader = new ArffLoader();
        loader.setSource(new File("src/main/resources/FoodChoice.arff"));
        Instances data = loader.getDataSet();

        // ARFF 파일의 속성 중 알레르기 항목을 제거할 속성 인덱스 찾기
        int[] attributeIndicesToRemove = findAttributeIndicesToRemove(data, allergies);

        // Remove 필터를 사용하여 알레르기 항목 제거
        Remove remove = new Remove();
        remove.setAttributeIndicesArray(attributeIndicesToRemove);
        remove.setInputFormat(data);
        Instances newData = Filter.useFilter(data, remove);

        // 제거된 데이터를 ARFF 파일로 저장
        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);
        saver.setFile(new File("src/main/resources/FoodChoiceModified.arff"));
        saver.writeBatch();
    }

    // ARFF 파일의 속성 중 알레르기 항목을 제거할 속성 인덱스를 찾는 메서드
    private int[] findAttributeIndicesToRemove(Instances data, List<String> allergies) {
        List<Integer> indices = new ArrayList<>();
        // 속성 이름과 알레르기 항목을 비교하여 제거할 속성 인덱스 찾기
        for (int i = 0; i < data.numAttributes(); i++) {
            String attributeName = data.attribute(i).name();
            if (allergies.contains(attributeName)) {
                indices.add(i + 1); // Weka는 인덱스를 1부터 시작합니다.
            }
        }
        return indices.stream().mapToInt(i -> i).toArray();
    }


}

