package com.library.service;

import com.library.domain.Reader;
import com.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReaderDbService {

    private final ReaderRepository repository;

    public List<Reader> getAllReaders() {
        return repository.findAll();
    }

    public Optional<Reader> getReader(Long id) {
        return repository.findById(id);
    }

    public Reader saveReader(Reader reader) {
        return repository.save(reader);
    }

    public void deleteReader(Long id) {
        repository.deleteById(id);
    }
}
