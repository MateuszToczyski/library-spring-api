package com.library.mapping;

import com.library.domain.Reader;
import com.library.domain.ReaderDto;
import org.springframework.stereotype.Component;

@Component
public class ReaderMapper {

    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(readerDto.getId(), readerDto.getName(), readerDto.getJoinDate());
    }

    public ReaderDto mapToReaderDto(Reader reader) {
        return new ReaderDto(reader.getId(), reader.getName(), reader.getJoinDate());
    }
}
