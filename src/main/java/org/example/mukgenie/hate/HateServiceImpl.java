package org.example.mukgenie.hate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Hate 관리 서비스 구현 클래스
@Service
public class HateServiceImpl implements HateService {

    private final HateRepository hateRepository;

    @Autowired
    public HateServiceImpl(HateRepository hateRepository) {
        this.hateRepository = hateRepository;
    }

    // 새로운 Hate 생성
    @Override
    public Hate createHate(Hate hate) {
        return hateRepository.save(hate);
    }

    // 모든 Hate 조회
    @Override
    public List<Hate> getAllHates() {
        return hateRepository.findAll();
    }

    // 특정 ID를 가진 Hate 조회
    @Override
    public Hate getHateById(String id) {
        return hateRepository.findById(id).orElse(null);
    }

    // 특정 ID를 가진 Hate 삭제
    @Override
    public void deleteHateById(String id) {
        hateRepository.deleteById(id);
    }
}
