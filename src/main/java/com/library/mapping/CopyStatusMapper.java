package com.library.mapping;

import com.library.domain.CopyStatus;
import com.library.domain.CopyStatusDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyStatusMapper {

    public CopyStatus mapToCopyStatus(CopyStatusDto copyStatusDto) {
        return new CopyStatus(copyStatusDto.getId(), copyStatusDto.getName());
    }

    public CopyStatusDto mapToCopyStatusDto(CopyStatus copyStatus) {
        return new CopyStatusDto(copyStatus.getId(), copyStatus.getName());
    }

    public List<CopyStatusDto> mapToCopyStatusDtoList(List<CopyStatus> copyStatuses) {
        return copyStatuses.stream()
                .map(this::mapToCopyStatusDto)
                .collect(Collectors.toList());
    }
}
