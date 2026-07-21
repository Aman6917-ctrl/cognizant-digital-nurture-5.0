package com.cognizant.spring.library.service;

import com.cognizant.spring.library.model.Book;
import com.cognizant.spring.library.repository.BookJpaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    private final BookJpaRepository bookJpaRepository;

    public BookService(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    public List<Book> findAll() {
        return bookJpaRepository.findAll();
    }

    public Book findById(Long id) {
        return bookJpaRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book create(Book book) {
        book.setId(null);
        return bookJpaRepository.save(book);
    }

    public Book update(Long id, Book updated) {
        Book existing = findById(id);
        existing.setTitle(updated.getTitle());
        existing.setAuthor(updated.getAuthor());
        return bookJpaRepository.save(existing);
    }

    public void delete(Long id) {
        if (!bookJpaRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookJpaRepository.deleteById(id);
    }
}
