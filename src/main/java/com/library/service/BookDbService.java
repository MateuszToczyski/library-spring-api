package com.library.service;

import com.library.domain.Book;
import com.library.domain.Copy;
import com.library.exception.BookNotFoundException;
import com.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookDbService {

    private final BookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBook(Long id) {
        return repository.findById(id);
    }

    public Book saveBook(Book book) {
        return repository.save(book);
    }

    public List<Copy> getAvailableCopies(Long bookId) {
        Book book = repository.findById(bookId).orElseThrow(BookNotFoundException::new);
        return book.getCopies().stream()
                .filter(copy -> copy.getStatus().getId().equals(1L))
                .collect(Collectors.toList());
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
