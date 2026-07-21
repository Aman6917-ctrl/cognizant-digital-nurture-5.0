package com.cognizant.spring.library.service;

public class CatalogReporter {

    private BookService bookService;

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public String injectionStyle() {
        return "setter-injected " + bookService.getClass().getSimpleName();
    }

    public void publishCatalogSummary() {
        int count = bookService.listBooks().size();
        System.out.println("CatalogReporter: library contains " + count + " book(s).");
        bookService.displayCatalog();
    }
}
