package com.library.repository;

import com.library.domain.Borrow;
import com.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BorrowRepository extends CrudRepository<Borrow, Long> {
    @Override
    List<Borrow> findAll();
    List<Borrow> findByReader(Reader reader);
}
