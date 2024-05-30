package org.example.mukgenie.food;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

// 사용자 정보를 나타내는 모델 클래스
@Document(collection = "Food")
@Getter
@Setter
public class Food {

    @Id
    private String id; // 사용자 ID

    private String name;//이름
    private String category; //나라
    private String ingredient; //재료
    private Integer temperature; //온도(1~3 숫자가 낮을 수록 뜨거움)
    private Boolean spiciness; //맵기
    private Boolean broth;//국물
    private Boolean oiliness;// 기름기
    private String cookingType;// 조리타입
}