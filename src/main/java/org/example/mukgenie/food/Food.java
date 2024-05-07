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
    private String country; //나라
    private String type; //종류
    private Integer spicy; //맵기수치(1~10)
    private Integer size; //음식 용량(ex: 1인분, 2인분)

    // 생성자, getter, setter는 생략되어 있습니다.
}
