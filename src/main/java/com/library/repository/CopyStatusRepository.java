package com.library.repository;

import com.library.domain.CopyStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CopyStatusRepository extends CrudRepository<CopyStatus, Long> {
    @Override
    List<CopyStatus> findAll();
}
