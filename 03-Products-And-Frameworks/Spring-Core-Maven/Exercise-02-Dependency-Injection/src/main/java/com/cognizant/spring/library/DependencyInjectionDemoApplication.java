package com.cognizant.spring.library;

import com.cognizant.spring.library.repository.BookRepository;
import com.cognizant.spring.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class DependencyInjectionDemoApplication {

    private DependencyInjectionDemoApplication() {
    }

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml")) {
            BookRepository repository = context.getBean("bookRepository", BookRepository.class);
            BookService bookService = context.getBean("bookService", BookService.class);

            System.out.println("Spring context loaded successfully.");
            System.out.println("Dependency injection: BookService received repository bean "
                    + repository.getClass().getSimpleName());
            System.out.println("Initial catalog size: " + bookService.listBooks().size());

            bookService.displayCatalog();
            bookService.registerBook("Clean Code", "Robert C. Martin");
            bookService.displayCatalog();
        }
    }
}
