package org.example.mukgenie.log;

import java.util.List;

public interface LogService {
    List<String> getFoodsByUserId(String userId);
}
