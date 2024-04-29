package org.example.mukgenie;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// 사용자 정보를 나타내는 모델 클래스
@Document(collection = "User")
public class User {

    @Id
    private String Id; // 사용자 ID

    private String name; // 사용자 이름
    private Integer age; // 사용자 나이
    private String user_id; // 사용자 아이디
    private String user_pw; // 사용자 비밀번호

    // 생성자, getter, setter는 생략되어 있습니다.
}
