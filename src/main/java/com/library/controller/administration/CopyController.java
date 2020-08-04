package com.library.controller.administration;

import com.library.domain.BorrowDto;
import com.library.domain.CopyDto;
import com.library.exception.CopyNotFoundException;
import com.library.mapping.BorrowMapper;
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
    private final CopyMapper copyMapper;
    private final BorrowMapper borrowMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<CopyDto> getAllCopies() {
        return copyMapper.mapToCopyDtoList(dbService.getAllCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public CopyDto getCopy(@PathVariable Long id) {
        return copyMapper.mapToCopyDto(dbService.getCopy(id).orElseThrow(CopyNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "{bookId}")
    public CopyDto createCopy(@PathVariable Long bookId) {
        return copyMapper.mapToCopyDto(dbService.createCopy(bookId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{copyId}/borrow/{readerId}")
    public CopyDto borrowCopy(@PathVariable Long copyId, @PathVariable Long readerId) {
        return copyMapper.mapToCopyDto(dbService.borrowCopy(copyId, readerId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/return")
    public CopyDto returnCopy(@PathVariable Long id) {
        return copyMapper.mapToCopyDto(dbService.returnCopy(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}/borrowHistory")
    public List<BorrowDto> getBorrowHistory(@PathVariable Long id) {
        return borrowMapper.mapToBorrowDtoList(dbService.getBorrowHistory(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteCopy(@PathVariable Long id) {
        dbService.deleteCopy(id);
    }
}
