    package org.example.mukgenie.user;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import java.util.List;

    // 사용자 관련 HTTP 요청을 처리하는 컨트롤러
    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        // 새로운 사용자 생성
        @PostMapping
        public User signUp(@RequestBody User user) {
            return userService.signUp(user);
        }

        // 모든 사용자 조회
        @GetMapping
        public List<User> getAllUsers() {
            return userService.getAllUsers();
        }

        // 특정 userID를 가진 사용자 조회
        @GetMapping("/UserId/{userId}")
        public User getUserByUserId(@PathVariable String userId) {
            return userService.getUserByUserId(userId);
        }

    }
