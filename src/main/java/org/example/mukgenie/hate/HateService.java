package org.example.mukgenie.hate;

import java.io.IOException;
import java.util.List;

public interface HateService {
    // 새로운 Hate 생성
    Hate createHate(Hate hate);

    // 모든 Hate 조회
    List<Hate> getAllHates();

    // 특정 ID를 가진 Hate 조회
    Hate getHateById(String id);

    // 특정 ID를 가진 Hate 삭제
    void deleteHateById(String id);

    void removeItemsFromARFF (List<String> allergies) throws IOException;
}