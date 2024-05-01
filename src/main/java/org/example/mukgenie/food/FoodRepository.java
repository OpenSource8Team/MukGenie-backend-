package org.example.mukgenie.food;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// 사용자 정보에 대한 MongoDB 저장소를 다루는 리포지토리 인터페이스
@Repository
public interface FoodRepository extends MongoRepository<Food, String> {

}
