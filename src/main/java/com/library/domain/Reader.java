package com.library.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "readers")
@Getter
@Setter
public class Reader implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(targetEntity = Borrow.class, mappedBy = "reader", cascade = CascadeType.ALL)
    private final List<Borrow> borrows = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "readers_roles",
            joinColumns = {@JoinColumn(name = "reader_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();

    public Reader() {
    }

    public Reader(Long id, String name, LocalDate joinDate, String password, String email) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.email = email;
    }

    public Reader(String name, LocalDate joinDate, String password, String email) {
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
