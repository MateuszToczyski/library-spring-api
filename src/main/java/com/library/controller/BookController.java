package com.library.controller;

import com.library.domain.BookDto;
import com.library.exception.BookNotFoundException;
import com.library.mapping.BookMapper;
import com.library.service.BookDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/books")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BookController {

    private final BookDbService dbService;
    private final BookMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookDto> getBooks() {
        return mapper.mapToBookDtoList(dbService.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public BookDto getBook(@PathVariable Long id) {
        return mapper.mapToBookDto(dbService.getBook(id).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return mapper.mapToBookDto(dbService.saveBook(mapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return mapper.mapToBookDto(dbService.saveBook(mapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteBook(@PathVariable Long id) {
        dbService.deleteBook(id);
    }
}
