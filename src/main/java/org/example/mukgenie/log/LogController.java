package org.example.mukgenie.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    private final LogService logService;

    // @Autowired: Spring이 자동으로 필요한 의존성을 주입합니다.
    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    // 특정 사용자 ID로 음식 목록을 조회하는 메서드
    // GET http://localhost:8080/log/UserId/{userId}
    @GetMapping("/UserId/{userId}")
    public List<String> getFoodsByUserId(@PathVariable String userId) {
        return logService.getFoodsByUserId(userId);
    }

    // 특정 사용자 ID와 음식 이름으로 로그에서 항목을 삭제하는 메서드
    // DELETE http://localhost:8080/log/delete/{userId}/{foodName}
    @DeleteMapping("/delete/{userId}/{foodName}")
    public void deleteFoodByName(@PathVariable String userId, @PathVariable String foodName) {
        logService.deleteFoodByName(userId, foodName);
    }

    // 특정 사용자 ID와 음식 이름으로 로그에 항목을 추가하는 메서드
    // POST http://localhost:8080/log/add/{userId}/{foodName}
    @PostMapping("/add/{userId}/{foodName}")
    public void addFood(@PathVariable String userId, @PathVariable String foodName) {
        logService.addFood(userId, foodName);
    }
}
