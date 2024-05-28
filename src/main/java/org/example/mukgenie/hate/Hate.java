package org.example.mukgenie.hate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

// 사용자 정보를 나타내는 모델 클래스
@Document(collection = "Hate")
@Getter
@Setter
public class Hate {

    @Id
    private String id; // 사용자 ID

    private String name; // 이름
    private List<String> foods; // 싫어하는 음식 목록

    // 생성자, getter, setter는 Lombok에 의해 자동 생성됩니다.
}
