package com.library.controller;

import com.library.domain.BorrowDto;
import com.library.mapping.BorrowMapper;
import com.library.service.BorrowDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/borrows")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class BorrowController {

    private final BorrowDbService dbService;
    private final BorrowMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<BorrowDto> getBorrows() {
        return mapper.mapToBorrowDtoList(dbService.getAllBorrows());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public BorrowDto getBorrow(@PathVariable Long id) {
        return mapper.mapToBorrowDto(dbService.getBorrow(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getByReader/{readerId}")
    public List<BorrowDto> getByReaderId(@PathVariable Long readerId) {
        return mapper.mapToBorrowDtoList(dbService.getBorrowsByReaderId(readerId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "start/{copyId}/{readerId}")
    public BorrowDto start(@PathVariable Long copyId, @PathVariable Long readerId) {
        return mapper.mapToBorrowDto(dbService.startBorrow(copyId, readerId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "end/{id}")
    public BorrowDto end(@PathVariable Long id) {
        return mapper.mapToBorrowDto(dbService.endBorrow(id));
    }
}
