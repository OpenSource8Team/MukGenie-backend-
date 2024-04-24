package org.example.mukgenie;

import org.example.mukgenie.DB.MongoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
@Import(MongoConfig.class) // MongoConfig 클래스를 가져옴
public class MukGenieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MukGenieApplication.class, args);
    }

}
