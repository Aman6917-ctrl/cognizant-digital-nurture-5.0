package com.cognizant.spring.library.repository;

import com.cognizant.spring.library.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryBookRepository implements BookRepository {

    private final Map<Long, Book> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public InMemoryBookRepository() {
        save(new Book(null, "Spring in Action", "Craig Walls"));
        save(new Book(null, "Effective Java", "Joshua Bloch"));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Book save(Book book) {
        Long id = book.getId() != null ? book.getId() : sequence.getAndIncrement();
        Book persisted = new Book(id, book.getTitle(), book.getAuthor());
        storage.put(id, persisted);
        return persisted;
    }
}
