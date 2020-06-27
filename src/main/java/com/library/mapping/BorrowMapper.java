package com.library.mapping;

import com.library.domain.Borrow;
import com.library.domain.BorrowDto;
import com.library.domain.Copy;
import com.library.domain.Reader;
import com.library.exception.CopyNotFoundException;
import com.library.exception.ReaderNotFoundException;
import com.library.service.CopyDbService;
import com.library.service.ReaderDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BorrowMapper {

    private final CopyDbService copyDbService;
    private final ReaderDbService readerDbService;

    public Borrow mapToBorrow(BorrowDto borrowDto) {
        Long id = borrowDto.getId();
        Copy copy = copyDbService.getCopy(borrowDto.getCopyId()).orElseThrow(CopyNotFoundException::new);
        Reader reader = readerDbService.getReader(borrowDto.getReaderId()).orElseThrow(ReaderNotFoundException::new);
        LocalDate startDate = borrowDto.getStartDate();
        LocalDate endDate = borrowDto.getEndDate();
        return new Borrow(id, copy, reader, startDate, endDate);
    }

    public BorrowDto mapToBorrowDto(Borrow borrow) {
        Long id = borrow.getId();
        Long copyId = borrow.getCopy().getId();
        Long readerId = borrow.getReader().getId();
        LocalDate startDate = borrow.getStartDate();
        LocalDate endDate = borrow.getEndDate();
        return new BorrowDto(id, copyId, readerId, startDate, endDate);
    }

    public List<BorrowDto> mapToBorrowDtoList(List<Borrow> borrows) {
        return borrows.stream()
                .map(this::mapToBorrowDto)
                .collect(Collectors.toList());
    }
}
