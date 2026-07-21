package com.cognizant.spring.library;

import com.cognizant.spring.library.service.BookService;
import com.cognizant.spring.library.service.CatalogReporter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class InjectionStylesDemoApplication {

    private InjectionStylesDemoApplication() {
    }

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            CatalogReporter catalogReporter = context.getBean("catalogReporter", CatalogReporter.class);

            System.out.println("Constructor injection: BookService -> " + bookService.injectionStyle());
            System.out.println("Setter injection: CatalogReporter -> " + catalogReporter.injectionStyle());

            catalogReporter.publishCatalogSummary();
            bookService.registerBook("Design Patterns", "GoF");
            catalogReporter.publishCatalogSummary();
        }
    }
}
