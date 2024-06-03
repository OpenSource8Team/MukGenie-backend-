package org.example.mukgenie.hate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

// Hate 관련 HTTP 요청을 처리하는 컨트롤러
@RestController
@RequestMapping("/hate")
public class HateController {

    private final HateService hateService;

    @Autowired
    public HateController(HateService hateService) {
        this.hateService = hateService;
    }

    // 새로운 Hate 생성
    @PostMapping
    public Hate createHate(@RequestBody Hate hate) {
        return hateService.createHate(hate);
    }

    // 모든 Hate 조회
    @GetMapping
    public List<Hate> getAllHates() {
        return hateService.getAllHates();
    }

    // 특정 ID를 가진 Hate 조회
    @GetMapping("/{id}")
    public Hate getHateById(@PathVariable String id) {
        return hateService.getHateById(id);
    }

    // 특정 ID를 가진 Hate 삭제
    @DeleteMapping("/{id}")
    public void deleteHateById(@PathVariable String id) {
        hateService.deleteHateById(id);
    }

    // 특정 타입들의 음식들 확인
    @GetMapping("/allergy")
    public void getAllergyItems(@RequestParam("allergies") List<String> allergies) throws IOException {
        hateService.removeItemsFromARFF(allergies);
    }

}