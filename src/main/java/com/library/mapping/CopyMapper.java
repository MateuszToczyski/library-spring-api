package com.library.mapping;

import com.library.domain.Copy;
import com.library.domain.CopyDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CopyMapper {

    private final BookMapper bookMapper;
    private final CopyStatusMapper statusMapper;

    public Copy mapToCopy(CopyDto copyDto) {
        return new Copy(copyDto.getId(), bookMapper.mapToBook(copyDto.getBook()),
                statusMapper.mapToCopyStatus(copyDto.getStatus()));
    }

    public CopyDto mapToCopyDto(Copy copy) {
        return new CopyDto(copy.getId(), bookMapper.mapToBookDto(copy.getBook()),
                statusMapper.mapToCopyStatusDto(copy.getStatus()));
    }

    public List<CopyDto> mapToCopyDtoList(List<Copy> copies) {
        return copies.stream()
                .map(copy -> new CopyDto(copy.getId(), bookMapper.mapToBookDto(copy.getBook()),
                        statusMapper.mapToCopyStatusDto(copy.getStatus())))
                .collect(Collectors.toList());
    }
}
