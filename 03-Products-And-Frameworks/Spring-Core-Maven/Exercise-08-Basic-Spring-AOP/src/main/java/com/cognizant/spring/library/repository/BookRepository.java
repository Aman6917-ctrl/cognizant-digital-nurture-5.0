package com.cognizant.spring.library.repository;

import com.cognizant.spring.library.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(Book book);
}
