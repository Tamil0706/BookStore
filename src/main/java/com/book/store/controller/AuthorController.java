package com.book.store.controller;

import com.book.store.exception.BookException;
import com.book.store.service.AuthorService;
import com.book.store.utils.BookConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private AuthorService authorService;

    @GetMapping("/author/{authorId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> getAuthor(@PathVariable long authorId) {
        try {
            logger.info("get book by author id all books");
            return ResponseEntity.ok(authorService.getAuthor(authorId));
        } catch (BookException e) {
            logger.error("Error occurred while getting book by author", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BookConstants.INTERNAL_SERVER_ERROR_MSG);
        }
    }
}
