package com.library.controller.administration;

import com.library.domain.BookDto;
import com.library.domain.CopyDto;
import com.library.exception.BookNotFoundException;
import com.library.mapping.BookMapper;
import com.library.mapping.CopyMapper;
import com.library.service.BookDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("administration/books")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BookController {

    private final BookDbService dbService;
    private final BookMapper bookMapper;
    private final CopyMapper copyMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(dbService.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookMapper.mapToBookDto(dbService.getBook(id).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}/availableCopies")
    public List<CopyDto> getAvailableCopies(@PathVariable Long id) {
        return copyMapper.mapToCopyDtoList(dbService.getAvailableCopies(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(dbService.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(dbService.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteBook(@PathVariable Long id) {
        dbService.deleteBook(id);
    }
}
