package com.cognizant.spring.library;

import com.cognizant.spring.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class AopLoggingDemoApplication {

    private AopLoggingDemoApplication() {
    }

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            System.out.println("Spring AOP logging enabled (Around advice on BookService).");
            bookService.displayCatalog();
            bookService.registerBook("Domain-Driven Design", "Eric Evans");
            bookService.displayCatalog();
        }
    }
}
