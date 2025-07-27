package org.admissio.backend.service;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.Major;
import org.admissio.backend.repository.MajorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    public List<Major> findAll() {
        return (List<Major>) majorRepository.findAll();
    }
}
