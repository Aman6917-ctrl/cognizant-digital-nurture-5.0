package com.cognizant.spring.library.web;

import com.cognizant.spring.library.model.Book;
import com.cognizant.spring.library.service.BookService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Illustrates spring-webmvc on the classpath; not registered as a Spring bean in this exercise.
 */
@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> listBooks() {
        return bookService.listBooks();
    }
}
