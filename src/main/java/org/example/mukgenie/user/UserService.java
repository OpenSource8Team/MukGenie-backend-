package org.example.mukgenie.user;

import java.util.List;

// 사용자 관리 서비스 인터페이스
public interface UserService {
    // 새로운 사용자 생성
    User createUser(User user);

    // 모든 사용자 조회
    List<User> getAllUsers();

    // 특정 ID를 가진 사용자 조회
    User getUserById(String id);

    // 특정 ID를 가진 사용자 삭제
    void deleteUserById(String id);
}
