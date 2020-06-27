package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BorrowDto {
    private final Long id;
    private final Long copyId;
    private final Long readerId;
    private final LocalDate startDate;
    private final LocalDate endDate;
}
