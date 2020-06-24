package com.library.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity(name = "copies")
@Getter
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private CopyStatus status;

    public Copy() {
    }

    public Copy(Book book, CopyStatus status) {
        this.book = book;
        this.status = status;
    }

    public Copy(Long id, Book book, CopyStatus status) {
        this.id = id;
        this.book = book;
        this.status = status;
    }
}
