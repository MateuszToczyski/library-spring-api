package com.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "copies")
@Getter
@Setter
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

    @OneToMany(targetEntity = Borrow.class, mappedBy = "copy", cascade = CascadeType.ALL)
    private final List<Borrow> borrows = new ArrayList<>();

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
