package com.cognizant.spring.library.service;

import com.cognizant.spring.library.model.Book;
import com.cognizant.spring.library.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
