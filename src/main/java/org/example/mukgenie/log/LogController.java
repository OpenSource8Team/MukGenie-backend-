package org.example.mukgenie.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/UserId/{userId}")
    public List<String> getFoodsByUserId(@PathVariable String userId) {
        return logService.getFoodsByUserId(userId);
    }

    @DeleteMapping("/delete/{userId}/{foodName}")
    public void deleteFoodByName(@PathVariable String userId, @PathVariable String foodName) {
        logService.deleteFoodByName(userId, foodName);
    }

    @PostMapping("/add/{userId}/{foodName}")
    public void addFood(@PathVariable String userId, @PathVariable String foodName) {
        logService.addFood(userId, foodName);
    }
}
