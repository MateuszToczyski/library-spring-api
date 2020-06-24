package com.library.controller;

import com.library.domain.CopyDto;
import com.library.exception.CopyNotFoundException;
import com.library.mapping.CopyMapper;
import com.library.service.CopyDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/copies")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CopyController {

    private final CopyDbService dbService;
    private final CopyMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<CopyDto> getAllCopies() {
        return mapper.mapToCopyDtoList(dbService.getAllCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableByBookId/{bookId}")
    public List<CopyDto> getAvailableCopiesByBookId(@PathVariable Long bookId) {
        return mapper.mapToCopyDtoList(dbService.getAvailableCopiesByBookId(bookId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public CopyDto getCopy(@PathVariable Long id) {
        return mapper.mapToCopyDto(dbService.getCopy(id).orElseThrow(CopyNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "{bookId}")
    public CopyDto createCopy(@PathVariable Long bookId) {
        return mapper.mapToCopyDto(dbService.createCopy(bookId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{copyId}/changeStatus/{statusId}")
    public CopyDto changeStatus(@PathVariable Long copyId, @PathVariable Long statusId) {
        return mapper.mapToCopyDto(dbService.changeCopyStatus(copyId, statusId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteCopy(@PathVariable Long id) {
        dbService.deleteCopy(id);
    }
}
