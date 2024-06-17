package org.example.mukgenie.hate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Hate 관련 HTTP 요청을 처리하는 컨트롤러
@RestController
@RequestMapping("/hate")
public class HateController {

    private final HateService hateService;

    // @Autowired: Spring이 자동으로 필요한 의존성을 주입합니다.
    @Autowired
    public HateController(HateService hateService) {
        this.hateService = hateService;
    }

    // 특정 알레르기 목록을 받아 해당 항목을 처리하는 메서드
    // GET http://localhost:8080/hate/allergy?allergies=육류&allergies=대두
    @GetMapping("/allergy")
    public void getAllergyItems(@RequestParam("allergies") List<String> allergies) throws Exception {
        // HateService의 createModifiedARFF 메서드를 호출하여 알레르기 데이터를 처리합니다.
        hateService.createModifiedARFF(allergies);
    }

}
