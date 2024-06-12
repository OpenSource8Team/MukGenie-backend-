package org.example.mukgenie.hate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // GET http://localhost:8080/hate/allergy?allergies=육류&allergies=대두
    @GetMapping("/allergy")
    public void getAllergyItems(@RequestParam("allergies") List<String> allergies) throws Exception {
        hateService.createModifiedARFF(allergies);
    }

}
