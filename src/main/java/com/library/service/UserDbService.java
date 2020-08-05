package com.library.service;

import com.library.domain.Borrow;
import com.library.domain.User;
import com.library.exception.UserNotFoundException;
import com.library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDbService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return repository.findById(id);
    }

    public User updateUser(User user) {
        return repository.save(user);
    }

    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public List<Borrow> getBorrowHistory(Long readerId) {
        User user = repository.findById(readerId).orElseThrow(UserNotFoundException::new);
        return user.getBorrows();
    }
}
