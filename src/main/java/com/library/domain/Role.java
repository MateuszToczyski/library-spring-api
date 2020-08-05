package com.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
