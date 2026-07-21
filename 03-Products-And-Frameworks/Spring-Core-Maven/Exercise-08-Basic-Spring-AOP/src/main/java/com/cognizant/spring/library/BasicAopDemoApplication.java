package com.cognizant.spring.library;

import com.cognizant.spring.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class BasicAopDemoApplication {

    private BasicAopDemoApplication() {
    }

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);

            System.out.println("Basic Spring AOP demo (@Before, @AfterReturning, @AfterThrowing).");
            bookService.displayCatalog();

            try {
                bookService.requireBook(999L);
            } catch (IllegalArgumentException expected) {
                System.out.println("Application handled: " + expected.getMessage());
            }
        }
    }
}
