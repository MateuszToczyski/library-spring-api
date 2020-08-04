package com.library.service;

import com.library.domain.*;
import com.library.exception.*;
import com.library.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CopyDbService {

    private final CopyRepository copyRepository;
    private final BookRepository bookRepository;
    private final CopyStatusRepository statusRepository;
    private final ReaderRepository readerRepository;

    public List<Copy> getAllCopies() {
        return copyRepository.findAll();
    }

    public Optional<Copy> getCopy(Long id) {
        return copyRepository.findById(id);
    }

    public Copy createCopy(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        CopyStatus status = statusRepository.findById(1L).orElseThrow(CopyStatusNotFoundException::new);
        Copy copy = new Copy(book, status);
        return copyRepository.save(copy);
    }

    public void deleteCopy(Long id) {
        copyRepository.deleteById(id);
    }

    public Copy borrowCopy(Long copyId, Long readerId) {
        Copy copy = copyRepository.findById(copyId).orElseThrow(CopyNotFoundException::new);
        if(!copy.getStatus().getId().equals(1L)) {
            throw new CopyStatusException("Copy already borrowed");
        }
        Reader reader = readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new);
        CopyStatus status = statusRepository.findById(2L).orElseThrow(CopyStatusNotFoundException::new);
        copy.setStatus(status);
        Borrow borrow = new Borrow(copy, reader, LocalDate.now());
        copy.getBorrows().add(borrow);
        return copyRepository.save(copy);
    }

    public Copy returnCopy(Long id) {
        Copy copy = copyRepository.findById(id).orElseThrow(CopyNotFoundException::new);
        if(!copy.getStatus().getId().equals(2L)) {
            throw new CopyStatusException("Copy already returned");
        }
        CopyStatus status = statusRepository.findById(1L).orElseThrow(CopyStatusNotFoundException::new);
        copy.setStatus(status);
        Borrow borrow = copy.getBorrows().get(copy.getBorrows().size() - 1);
        borrow.setEndDate(LocalDate.now());
        return copyRepository.save(copy);
    }

    public List<Borrow> getBorrowHistory(Long copyId) {
        Copy copy = copyRepository.findById(copyId).orElseThrow(CopyNotFoundException::new);
        return copy.getBorrows();
    }
}
