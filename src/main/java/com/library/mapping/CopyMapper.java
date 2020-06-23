package com.library.mapping;

import com.library.domain.Copy;
import com.library.domain.CopyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyMapper {

    public Copy mapToCopy(CopyDto copyDto) {
        return new Copy(copyDto.getId(), copyDto.getBook(), copyDto.getStatus());
    }

    public CopyDto mapToCopyDto(Copy copy) {
        return new CopyDto(copy.getId(), copy.getBook(), copy.getStatus());
    }

    public List<CopyDto> mapToCopyDtoList(List<Copy> copies) {
        return copies.stream()
                .map(copy -> new CopyDto(copy.getId(), copy.getBook(), copy.getStatus()))
                .collect(Collectors.toList());
    }
}
