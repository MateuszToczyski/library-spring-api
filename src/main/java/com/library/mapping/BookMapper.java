package com.library.mapping;

import com.library.domain.Book;
import com.library.domain.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book mapToBook(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPublicationDate());
    }

    public BookDto mapToBookDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationDate());
    }
}
