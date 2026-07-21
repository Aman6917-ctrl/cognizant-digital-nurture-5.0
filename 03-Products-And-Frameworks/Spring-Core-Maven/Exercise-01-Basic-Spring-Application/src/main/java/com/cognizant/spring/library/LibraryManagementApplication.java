package com.cognizant.spring.library;

import com.cognizant.spring.library.model.Book;
import com.cognizant.spring.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class LibraryManagementApplication {

    private LibraryManagementApplication() {
    }

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            System.out.println("Spring context loaded successfully.");
            bookService.displayCatalog();
            Book registered = bookService.registerBook("Clean Code", "Robert C. Martin");
            System.out.println("Registered: " + registered);
            bookService.displayCatalog();
        }
    }
}
