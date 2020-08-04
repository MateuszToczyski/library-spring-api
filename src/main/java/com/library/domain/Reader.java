package com.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "readers")
@Getter
@Setter
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @OneToMany(targetEntity = Borrow.class, mappedBy = "reader", cascade = CascadeType.ALL)
    private final List<Borrow> borrows = new ArrayList<>();

    public Reader() {
    }

    public Reader(Long id, String name, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
    }
}
