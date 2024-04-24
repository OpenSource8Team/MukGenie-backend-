package org.example.mukgenie.DB.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {

    @Id
    private String id;
    private String name;
    private String  user_id;
    private  String  user_pw;

    // 생성자, getter, setter 생략
}