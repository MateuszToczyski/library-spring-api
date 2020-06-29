package com.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Setter
    @Column(name = "end_date")
    private LocalDate endDate;

    public Borrow() {
    }

    public Borrow(Copy copy, Reader reader, LocalDate startDate) {
        this.copy = copy;
        this.reader = reader;
        this.startDate = startDate;
    }

    public Borrow(Long id, Copy copy, Reader reader, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.copy = copy;
        this.reader = reader;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
