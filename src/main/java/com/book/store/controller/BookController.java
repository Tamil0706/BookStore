package com.book.store.controller;



import com.book.store.exception.BookException;
import com.book.store.model.Book;
import com.book.store.model.UserDto;
import com.book.store.service.AuthorService;
import com.book.store.service.BookService;
import com.book.store.utils.BookConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/api"})
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;



    @GetMapping(value = "/books")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> getAllBooks() {
        try {
                return ResponseEntity.ok(bookService.getAllBooks());
        } catch (BookException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BookConstants.INTERNAL_SERVER_ERROR_MSG);
        }
    }

    @PostMapping(value = "/addbooks")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> addBook(@RequestBody List<Book> book) {
        try {
            logger.info("insert all books");
            return ResponseEntity.ok(bookService.saveBook(book));
        } catch (BookException e) {
            logger.error("Error occurred while adding books", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BookConstants.INTERNAL_SERVER_ERROR_MSG);
        }
    }

    @GetMapping("/bookbyisbn/{isbn}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> getBookByIsbn(@PathVariable long isbn) {
        try {
            logger.info("get Book By Isbn all books");
            return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
        } catch (BookException e) {
            logger.error("Error occurred while getting book bu Isbn", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BookConstants.INTERNAL_SERVER_ERROR_MSG);
        }
    }

    @PutMapping("/updatebooks/{isbn}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateBook(@PathVariable long isbn, @RequestBody Book bookDetails) {
        try {
            logger.info("update Book By Isbn all books");
            return ResponseEntity.ok(bookService.updateBook(isbn, bookDetails));
        } catch (BookException e) {
            logger.error("Error occurred while updating book by isbn", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BookConstants.INTERNAL_SERVER_ERROR_MSG);
        }
    }

    @DeleteMapping("/deletebooks/{isbn}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteBook(@PathVariable long isbn)  {
        try {
            logger.info("delete Book By Isbn all books");
            return ResponseEntity.ok(bookService.deleteBook(isbn));
        } catch (BookException e) {
            logger.error("Error occurred while deleteing book by Isbn", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BookConstants.INTERNAL_SERVER_ERROR_MSG);
        }
    }


    @PostMapping("/reviewbooks/{isbn}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> addReview(@PathVariable long isbn, @RequestBody Map<String, String> payload) {
        try {
            logger.info("add review to Book By Isbn all books");
            return ResponseEntity.ok(bookService.addReview(isbn, payload.get("review")));
        } catch (BookException e) {
            logger.error("Error occurred while adding review to book by Isbn", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BookConstants.INTERNAL_SERVER_ERROR_MSG);
        }
    }



}
