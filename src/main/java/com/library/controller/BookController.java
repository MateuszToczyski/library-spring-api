package com.library.controller;

import com.library.domain.BookDto;
import com.library.exception.BookNotFoundException;
import com.library.mapping.BookMapper;
import com.library.service.DbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BookController {

    private final DbService dbService;
    private final BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(@RequestParam Long id) {
        return bookMapper.mapToBookDto(dbService.getBook(id).orElseThrow(BookNotFoundException::new));
    }
}
