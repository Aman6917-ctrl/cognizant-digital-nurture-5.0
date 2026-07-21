package com.cognizant.spring.library;

import com.cognizant.spring.library.config.AppConfig;
import com.cognizant.spring.library.service.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class AnnotationConfigurationDemoApplication {

    private AnnotationConfigurationDemoApplication() {
    }

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class)) {
            BookService bookService = context.getBean(BookService.class);
            System.out.println("Java @Configuration loaded (no XML bean definitions).");
            System.out.println("BookService wired via @Autowired constructor/setter injection.");
            bookService.displayCatalog();
            bookService.registerBook("Release It!", "Nygard");
            bookService.displayCatalog();
        }
    }
}
