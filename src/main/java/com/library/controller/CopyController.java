package com.library.controller;

import com.library.domain.Book;
import com.library.domain.Copy;
import com.library.domain.CopyDto;
import com.library.domain.CopyStatus;
import com.library.exception.BookNotFoundException;
import com.library.exception.CopyNotFoundException;
import com.library.exception.CopyStatusNotFoundException;
import com.library.mapping.CopyMapper;
import com.library.service.BookDbService;
import com.library.service.CopyDbService;
import com.library.service.CopyStatusDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/copies")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CopyController {

    private final CopyDbService copyDbService;
    private final BookDbService bookDbService;
    private final CopyStatusDbService statusDbService;
    private final CopyMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<CopyDto> getCopies() {
        return mapper.mapToCopyDtoList(copyDbService.getAllCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public CopyDto getCopy(@PathVariable Long id) {
        return mapper.mapToCopyDto(copyDbService.getCopy(id).orElseThrow(CopyNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "{bookId}")
    public CopyDto createCopy(@PathVariable Long bookId) {
        Book book = bookDbService.getBook(bookId).orElseThrow(BookNotFoundException::new);
        CopyStatus status = statusDbService.getStatus(1L).orElseThrow(CopyStatusNotFoundException::new);
        Copy copy = new Copy(null, book, status);
        return mapper.mapToCopyDto(copyDbService.saveCopy(copy));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public CopyDto updateCopy(@RequestBody CopyDto copyDto) {
        return mapper.mapToCopyDto(copyDbService.saveCopy(mapper.mapToCopy(copyDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteCopy(@PathVariable Long id) {
        copyDbService.deleteCopy(id);
    }
}
