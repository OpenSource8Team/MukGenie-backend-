package org.example.mukgenie.log;

import java.util.List;

public interface LogService {
    List<String> getFoodsByUserId(String userId);
    void deleteFoodByName(String userId, String foodName);
    void addFood(String userId, String foodName);
}
