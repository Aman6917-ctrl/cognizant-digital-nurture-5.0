package com.cognizant.spring.library;

import com.cognizant.spring.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class MavenConfigurationDemoApplication {

    private MavenConfigurationDemoApplication() {
    }

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            System.out.println("Maven-managed Spring modules loaded (context, aop, webmvc on classpath).");
            System.out.println("Active beans: bookRepository, bookService");
            bookService.displayCatalog();
        }
    }
}
