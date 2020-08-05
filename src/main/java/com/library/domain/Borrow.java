package com.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "borrows")
@Getter
@Setter
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public Borrow() {
    }

    public Borrow(Copy copy, User user, LocalDate startDate) {
        this.copy = copy;
        this.user = user;
        this.startDate = startDate;
    }

    public Borrow(Long id, Copy copy, User user, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.copy = copy;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
