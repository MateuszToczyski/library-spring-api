package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private Long id;
    private String name;
    private LocalDate joinDate;
}
