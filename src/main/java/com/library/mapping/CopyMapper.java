package com.library.mapping;

import com.library.domain.Book;
import com.library.domain.Copy;
import com.library.domain.CopyDto;
import com.library.domain.CopyStatus;
import com.library.exception.BookNotFoundException;
import com.library.exception.CopyStatusNotFoundException;
import com.library.service.BookDbService;
import com.library.service.CopyStatusDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CopyMapper {

    private final BookDbService bookDbService;
    private final CopyStatusDbService statusDbService;

    public Copy mapToCopy(CopyDto copyDto) {
        Long id = copyDto.getId();
        Book book = bookDbService.getBook(copyDto.getBookId()).orElseThrow(BookNotFoundException::new);
        CopyStatus status = statusDbService.getStatus(copyDto.getStatusId()).orElseThrow(CopyStatusNotFoundException::new);
        return new Copy(id, book, status);
    }

    public CopyDto mapToCopyDto(Copy copy) {
        Long id = copy.getId();
        Long bookId = copy.getBook().getId();
        Long statusId = copy.getStatus().getId();
        return new CopyDto(id, bookId, statusId);
    }

    public List<CopyDto> mapToCopyDtoList(List<Copy> copies) {
        return copies.stream()
                .map(this::mapToCopyDto)
                .collect(Collectors.toList());
    }
}
