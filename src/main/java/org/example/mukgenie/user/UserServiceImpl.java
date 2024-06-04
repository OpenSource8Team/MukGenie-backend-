package org.example.mukgenie.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User signUp(User user) {
        // 사용자 비밀번호 해싱
        String hashedPassword = bCryptPasswordEncoder.encode(user.getUserPw());
        user.setUserPw(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User login(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getUserPw())) {
            return user;
        }
        return null; // 아이디나 비밀번호가 일치하지 않는 경우 null 반환
    }

    @Override
    public boolean isUserIdExist(String userId) {
        User user = userRepository.findByUserId(userId);
        return user != null;
    }
}
