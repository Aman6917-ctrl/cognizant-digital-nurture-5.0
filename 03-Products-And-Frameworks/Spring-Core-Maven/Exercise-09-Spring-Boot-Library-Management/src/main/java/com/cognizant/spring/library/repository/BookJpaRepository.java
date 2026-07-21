package com.cognizant.spring.library.repository;

import com.cognizant.spring.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<Book, Long> {
}
