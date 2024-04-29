package org.example.mukgenie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// 사용자 관리 서비스 구현 클래스
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 새로운 사용자 생성
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // 모든 사용자 조회
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 특정 ID를 가진 사용자 조회
    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    // 특정 ID를 가진 사용자 삭제
    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
}
