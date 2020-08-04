package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserDto {
    private final Long id;
    private final String name;
    private final LocalDate joinDate;
    private final String password;
    private final String email;
}
