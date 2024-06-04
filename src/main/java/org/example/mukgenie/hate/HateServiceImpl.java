package org.example.mukgenie.hate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.Set;

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


    // 주어진 알러지 목록을 기반으로 ARFF 파일에서 해당 알러지에 해당하는 항목을 제거하여 새로운 파일 생성
    public void createModifiedARFF(List<String> allergies) throws IOException {
        // 알러지 항목을 담을 집합 생성 (중복 허용 안 함)
        Set<String> allergyItems = new HashSet<>();
        // 모든 Hate 객체를 조회
        List<Hate> hates = hateRepository.findAll();
        // 모든 Hate 객체에 대해 반복
        for (Hate hate : hates) {
            // 각 Hate 객체의 알러지 데이터 맵 조회
            Map<String, List<String>> allergyData = hate.getAllergy();
            // 요청으로 받은 알러지 유형들에 대해 반복
            for (String allergy : allergies) {
                // 알러지 데이터 맵에 해당 알러지가 존재하는지 확인
                if (allergyData.containsKey(allergy)) {
                    // 해당 알러지에 대한 항목들을 알러지 항목 집합에 추가
                    allergyItems.addAll(allergyData.get(allergy));
                }
            }
        }

        // 집합을 리스트로 변환
        List<String> allergyItemList = new ArrayList<>(allergyItems);

        // 기존 ARFF 파일 읽기
        File inputFile = new File("src/main/resources/FoodChoice.arff");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        // 새로운 ARFF 파일 생성
        File outputFile = new File("src/main/resources/FoodChoiceModified.arff");
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;
        boolean dataFound = false;
        while ((line = reader.readLine()) != null) {
            if (dataFound) {
                // @data 이후의 줄에 대해서만 처리
                boolean containsAllergyItem = false;
                for (String allergyItem : allergyItemList) {
                    if (line.contains(allergyItem)) {
                        containsAllergyItem = true;
                        break;
                    }
                }
                // allergyItemList에 포함된 단어가 없는 줄만 리스트에 추가
                if (!containsAllergyItem) {
                    writer.write(line + "\n");
                }
            } else {
                writer.write(line + "\n");
                if (line.trim().equals("@data")) {
                    // @data를 찾으면 이후의 줄부터 처리
                    dataFound = true;
                }
            }
        }
        reader.close();
        writer.close();
    }
}
