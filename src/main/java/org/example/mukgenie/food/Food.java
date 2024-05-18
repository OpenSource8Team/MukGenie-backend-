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

    private Integer personnel; //인원

    private Integer price; //가격

    private String type; //종류(면, 빵, 밥)

    private String allergy;//알러지

    private String cook;// 조리타입(굽기,삶기,찌기)

    private String dlike;// 못 먹는 음식

    private Integer calorie;


    // 생성자, getter, setter는 생략되어 있습니다.
}
