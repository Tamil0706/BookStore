package com.book.store.controller;

import com.book.store.exception.BookException;
import com.book.store.model.Author;
import com.book.store.service.AuthorService;
import com.book.store.utils.BookConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorService authorService;

    @BeforeEach
    public void init() {
    }

    @Test
    void testGetAuthor() throws BookException {
        long authorId = 1L;
        Author author = new Author();
        author.setAurthorid(authorId);
        author.setAuthorName("Author Name");

        when(authorService.getAuthor(authorId)).thenReturn(author);

        ResponseEntity<Object> response = authorController.getAuthor(authorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(author, response.getBody());
    }

    @Test
    void testGetAuthorException() throws BookException {
        long authorId = 1L;

        when(authorService.getAuthor(authorId)).thenThrow(new BookException("Error occurred while getting author"));

        ResponseEntity<Object> response = authorController.getAuthor(authorId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(BookConstants.INTERNAL_SERVER_ERROR_MSG, response.getBody());
    }
}
