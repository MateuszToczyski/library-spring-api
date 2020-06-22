package com.library.controller;

import com.library.domain.ReaderDto;
import com.library.exception.ReaderNotFoundException;
import com.library.mapping.ReaderMapper;
import com.library.service.ReaderDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/readers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ReaderController {

    private final ReaderDbService readerDbService;
    private final ReaderMapper readerMapper;

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ReaderDto getReader(@PathVariable Long id) {
         return readerMapper.mapToReaderDto(readerDbService.getReader(id).orElseThrow(ReaderNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ReaderDto createReader(@RequestBody ReaderDto readerDto) {
        return readerMapper.mapToReaderDto(readerDbService.saveReader(readerMapper.mapToReader(readerDto)));
    }
}
