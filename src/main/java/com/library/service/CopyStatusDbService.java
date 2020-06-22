package com.library.service;

import com.library.domain.CopyStatus;
import com.library.repository.CopyStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CopyStatusDbService {

    private final CopyStatusRepository repository;

    public List<CopyStatus> getAllStatuses() {
        return repository.findAll();
    }

    public Optional<CopyStatus> getStatus(Long id) {
        return repository.findById(id);
    }

    public CopyStatus saveStatus(CopyStatus copyStatus) {
        return repository.save(copyStatus);
    }

    public void deleteStatus(Long id) {
        repository.deleteById(id);
    }
}
