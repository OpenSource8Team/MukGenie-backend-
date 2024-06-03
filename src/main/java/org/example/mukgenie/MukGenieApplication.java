package org.example.mukgenie;

import org.example.mukgenie.food.FoodService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication(scanBasePackages = {"org.example.mukgenie"})
public class MukGenieApplication implements ApplicationRunner {

    private final FoodService foodService;

    public MukGenieApplication(FoodService foodService) {
        this.foodService = foodService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MukGenieApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        // 애플리케이션이 시작될 때 exportToArff 메서드를 호출합니다.
        foodService.exportToArff();
    }
}
