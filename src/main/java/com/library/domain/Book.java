package com.library.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "books")
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @OneToMany(targetEntity = Copy.class, mappedBy = "book", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Copy> copies;

    public Book() {
    }

    public Book(Long id, String title, String author, LocalDate publicationDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }
}
