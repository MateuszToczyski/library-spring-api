package com.library.repository;

import com.library.domain.Copy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CopyRepository extends CrudRepository<Copy, Long> {
    @Override
    List<Copy> findAll();
}
