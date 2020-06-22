package com.library.controller;

import com.library.domain.BookDto;
import com.library.exception.BookNotFoundException;
import com.library.mapping.BookMapper;
import com.library.service.DbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/books")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BookController {

    private final DbService dbService;
    private final BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookMapper.mapToBookDto(dbService.getBook(id).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(dbService.saveBook(bookMapper.mapToBook(bookDto)));
    }
}
