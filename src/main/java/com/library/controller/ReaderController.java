package com.library.controller;

import com.library.domain.Borrow;
import com.library.domain.ReaderDto;
import com.library.exception.ReaderNotFoundException;
import com.library.mapping.ReaderMapper;
import com.library.service.ReaderDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/readers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ReaderController {

    private final ReaderDbService dbService;
    private final ReaderMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<ReaderDto> getReaders() {
        return mapper.mapToReaderDtoList(dbService.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ReaderDto getReader(@PathVariable Long id) {
         return mapper.mapToReaderDto(dbService.getReader(id).orElseThrow(ReaderNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ReaderDto createReader(@RequestBody ReaderDto readerDto) {
        return mapper.mapToReaderDto(dbService.saveReader(mapper.mapToReader(readerDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public ReaderDto updateReader(@RequestBody ReaderDto readerDto) {
        return mapper.mapToReaderDto(dbService.saveReader(mapper.mapToReader(readerDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteReader(@PathVariable Long id) {
        dbService.deleteReader(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{readerId}")
    public List<Borrow> getBorrowHistory(@PathVariable Long readerId) {
        return dbService.getBorrowHistory(readerId);
    }
}
