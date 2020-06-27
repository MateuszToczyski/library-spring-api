package com.library.service;

import com.library.domain.Borrow;
import com.library.domain.Copy;
import com.library.domain.Reader;
import com.library.exception.BorrowNotFoundException;
import com.library.exception.CopyNotFoundException;
import com.library.exception.ReaderNotFoundException;
import com.library.repository.BorrowRepository;
import com.library.repository.CopyRepository;
import com.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowDbService {

    private final BorrowRepository borrowRepository;
    private final ReaderRepository readerRepository;
    private final CopyRepository copyRepository;

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow getBorrow(Long id) {
        return borrowRepository.findById(id).orElseThrow(BorrowNotFoundException::new);
    }

    public List<Borrow> getBorrowsByReaderId(Long readerId) {
        Reader reader = readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new);
        return borrowRepository.findByReader(reader);
    }

    public Borrow startBorrow(Long copyId, Long readerId) {
        Copy copy = copyRepository.findById(copyId).orElseThrow(CopyNotFoundException::new);
        Reader reader = readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new);
        LocalDate startDate = LocalDate.now();
        return borrowRepository.save(new Borrow(copy, reader, startDate));
    }

    public Borrow endBorrow(Long id) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow(BorrowNotFoundException::new);
        Borrow updatedBorrow = new Borrow(borrow.getId(), borrow.getCopy(), borrow.getReader(),
                borrow.getStartDate(), LocalDate.now());
        return borrowRepository.save(updatedBorrow);
    }
}
