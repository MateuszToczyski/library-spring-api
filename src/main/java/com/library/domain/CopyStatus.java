package com.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "copy_statuses")
@Getter
@Setter
public class CopyStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Copy.class, mappedBy = "status", fetch = FetchType.LAZY)
    private List<Copy> copies;

    public CopyStatus() {
    }

    public CopyStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
