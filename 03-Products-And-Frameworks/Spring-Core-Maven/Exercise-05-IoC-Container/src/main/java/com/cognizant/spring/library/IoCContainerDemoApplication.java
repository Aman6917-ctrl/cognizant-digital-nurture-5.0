package com.cognizant.spring.library;

import com.cognizant.spring.library.repository.BookRepository;
import com.cognizant.spring.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class IoCContainerDemoApplication {

    private IoCContainerDemoApplication() {
    }

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("IoC container type: " + context.getClass().getSimpleName());
        System.out.println("Registered bean names: " + String.join(", ", context.getBeanDefinitionNames()));

        BookRepository bookRepository = context.getBean("bookRepository", BookRepository.class);
        BookService bookService = context.getBean("bookService", BookService.class);

        System.out.println("Retrieved bookRepository -> " + bookRepository.getClass().getSimpleName());
        System.out.println("Retrieved bookService -> " + bookService.getClass().getSimpleName());
        bookService.displayCatalog();

        if (context instanceof ClassPathXmlApplicationContext xmlContext) {
            xmlContext.close();
        }
    }
}
