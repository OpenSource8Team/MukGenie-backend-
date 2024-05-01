package org.example.mukgenie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// 서버 실행 클레스

@EnableMongoRepositories
@SpringBootApplication(scanBasePackages = {"org.example.mukgenie"})
public class MukGenieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MukGenieApplication.class, args);
    }

}