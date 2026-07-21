package com.cognizant.spring.library.service;

import com.cognizant.spring.library.model.Book;
import com.cognizant.spring.library.repository.BookRepository;
import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String injectionStyle() {
        return "constructor-injected " + bookRepository.getClass().getSimpleName();
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public Book registerBook(String title, String author) {
        return bookRepository.save(new Book(null, title, author));
    }

    public void displayCatalog() {
        System.out.println("=== Library Catalog ===");
        listBooks().forEach(book -> System.out.println(" - " + book));
    }
}
