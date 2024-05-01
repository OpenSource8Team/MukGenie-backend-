package org.example.mukgenie.food;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// 사용자 정보를 나타내는 모델 클래스
@Document(collection = "Food")
@Getter
@Setter
public class Food {

    @Id
    private String id; // 사용자 ID
    private String name;//이름


    // 생성자, getter, setter는 생략되어 있습니다.
}
