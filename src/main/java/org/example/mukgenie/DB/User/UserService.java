package org.example.mukgenie.DB.User;

import org.example.mukgenie.DB.User.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(String id);
    void deleteUserById(String id);
}