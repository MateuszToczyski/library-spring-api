package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BookDto {
    private final Long id;
    private final String title;
    private final String author;
    private final LocalDate publicationDate;
}
