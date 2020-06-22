package com.library.service;

import com.library.domain.Copy;
import com.library.repository.CopyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CopyDbService {

    private final CopyRepository repository;

    public List<Copy> getAllCopies() {
        return repository.findAll();
    }

    public Optional<Copy> getCopy(Long id) {
        return repository.findById(id);
    }

    public Copy saveCopy(Copy copy) {
        return repository.save(copy);
    }

    public void deleteCopy(Long id) {
        repository.deleteById(id);
    }
}
