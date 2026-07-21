package com.cognizant.spring.library.service;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Book not found for id " + id);
    }
}
