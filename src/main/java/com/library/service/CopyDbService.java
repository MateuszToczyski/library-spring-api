package com.library.service;

import com.library.domain.Book;
import com.library.domain.Copy;
import com.library.domain.CopyStatus;
import com.library.exception.BookNotFoundException;
import com.library.exception.CopyNotFoundException;
import com.library.exception.CopyStatusNotFoundException;
import com.library.repository.BookRepository;
import com.library.repository.CopyRepository;
import com.library.repository.CopyStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CopyDbService {

    private final CopyRepository copyRepository;
    private final BookRepository bookRepository;
    private final CopyStatusRepository statusRepository;

    public List<Copy> getAllCopies() {
        return copyRepository.findAll();
    }

    public List<Copy> getAvailableCopiesByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        return copyRepository.findByBook(book);
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

    public Copy changeCopyStatus(Long copyId, Long statusId) {
        Copy copy = copyRepository.findById(copyId).orElseThrow(CopyNotFoundException::new);
        CopyStatus status = statusRepository.findById(statusId).orElseThrow(CopyStatusNotFoundException::new);
        Copy updatedCopy = new Copy(copy.getId(), copy.getBook(), status);
        return copyRepository.save(updatedCopy);
    }

    public void deleteCopy(Long id) {
        copyRepository.deleteById(id);
    }
}
