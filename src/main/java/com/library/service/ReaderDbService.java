package com.library.service;

import com.library.domain.Reader;
import com.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReaderDbService {

    private final ReaderRepository readerRepository;

    public Optional<Reader> getReader(Long id) {
        return readerRepository.findById(id);
    }

    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }
}
