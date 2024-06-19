package com.book.store.service;


import com.book.store.exception.BookException;
import com.book.store.model.Book;
import com.book.store.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;



    public List<Book> getAllBooks() throws BookException {
        logger.info("Getting all books");
        return bookRepository.findAll();
    }

    public String saveBook(List<Book> book) throws BookException {
        if (book.isEmpty()) {
            throw new BookException("Book list is empty");
        }
        bookRepository.saveAll(book);
        return "Save";
    }


    public Book getBookByIsbn(long isbn) throws BookException {
        return bookRepository.findById(isbn)
                .orElseThrow(() -> new BookException("Book by ISBN : " + isbn + " does not exist."));
    }


    public Book updateBook(long isbn, Book bookDetails) throws BookException {
        return bookRepository.findById(isbn)
                .map(existingBook -> {
                    existingBook.setBookName(bookDetails.getBookName());
                    existingBook.setPrice(bookDetails.getPrice());
                    existingBook.setCategories(bookDetails.getCategories());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new BookException("Book not found with isbn : " + isbn));
    }


    public String deleteBook(long isbn) throws BookException {
        return bookRepository.findById(isbn)
                .map(book -> {
                    bookRepository.delete(book);
                    return "Book with ISBN :" + isbn + " was deleted.";
                })
                .orElseThrow(() -> new BookException("Book with ISBN :" + isbn + " not found."));
    }



    public Book addReview(long isbn, String review) throws BookException {
        return bookRepository.findById(isbn)
                .map(book -> {
                    book.setReview(review);
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new BookException("Book not found"));
    }

    
}
