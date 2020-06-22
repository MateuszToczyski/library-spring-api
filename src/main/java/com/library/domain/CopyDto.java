package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CopyDto {
    private final Long id;
    private final Book book;
    private final CopyStatus status;
}
