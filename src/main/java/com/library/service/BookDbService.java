package com.library.service;

import com.library.domain.Book;
import com.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookDbService {

    private final BookRepository bookRepository;

    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
