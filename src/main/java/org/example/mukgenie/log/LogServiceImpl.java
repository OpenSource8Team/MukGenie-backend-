package org.example.mukgenie.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<String> getFoodsByUserId(String userId) {
        Log log = logRepository.findByUserId(userId);
        if (log != null) {
            return log.getFoods();
        } else {
            return Collections.emptyList();
        }
    }
}
