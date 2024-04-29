package org.example.mukgenie;

import org.springframework.data.mongodb.repository.MongoRepository;

// 사용자 정보에 대한 MongoDB 저장소를 다루는 리포지토리 인터페이스
public interface UserRepository extends MongoRepository<User, String> {

}
