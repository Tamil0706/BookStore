package com.book.store.controller;



import com.book.store.exception.BookException;
import com.book.store.model.Author;
import com.book.store.model.Book;
import com.book.store.service.AuthorService;
import com.book.store.service.BookService;
import com.book.store.utils.BookConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;


    @MockBean
    private AuthorService authorService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testGetAllBooks() throws BookException {
        Book book1 = new Book();
        book1.setIsbn(123L);
        Book book2 = new Book();
        book2.setIsbn(456L);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<Object> response = bookController.getAllBooks();


        assertEquals(200, response.getStatusCodeValue());
        assertEquals(books, response.getBody());
    }

    @Test
    void testGetAllBooksException() throws BookException {
        when(bookService.getAllBooks()).thenThrow(new BookException("Error occurred while getting all books"));

        ResponseEntity<Object> response = bookController.getAllBooks();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(BookConstants.INTERNAL_SERVER_ERROR_MSG, response.getBody());
    }


    @Test
    void testAddBook() throws BookException {
        Book book1 = new Book();
        book1.setIsbn(123L);
        Book book2 = new Book();
        book2.setIsbn(456L);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.saveBook(books)).thenReturn("Save");

        ResponseEntity<Object> response = bookController.addBook(books);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Save", response.getBody());
    }

    @Test
    void testAddBookException() throws BookException {
        Book book1 = new Book();
        book1.setIsbn(123L);
        Book book2 = new Book();
        book2.setIsbn(456L);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.saveBook(books)).thenThrow(new BookException("Error occurred"));

        ResponseEntity<Object> response =  bookController.addBook(books);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(BookConstants.INTERNAL_SERVER_ERROR_MSG, response.getBody());
    }


    @Test
    void testGetBookByIsbn() throws BookException {
        Book book = new Book();
        book.setIsbn(123L);

        when(bookService.getBookByIsbn(123L)).thenReturn(book);

        ResponseEntity<Object> response = bookController.getBookByIsbn(123L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(book, response.getBody());
    }

    @Test
    void testGetBookByIsbnException() throws BookException {
        long isbn = 123L;

        when(bookService.getBookByIsbn(isbn)).thenThrow(new BookException("Book not found"));

        ResponseEntity<Object> result = bookController.getBookByIsbn(isbn);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals(BookConstants.INTERNAL_SERVER_ERROR_MSG, result.getBody());
    }


    @Test
    void testDeleteBook() throws BookException {
        long isbn = 123L;
        String expectedResponse = "Book with ISBN " + isbn + " was deleted.";

        when(bookService.deleteBook(isbn)).thenReturn(expectedResponse);

        ResponseEntity<Object>  result = bookController.deleteBook(isbn);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponse, result.getBody());
    }



    @Test
    void testDeleteBookException() throws BookException {
        long isbn = 123L;

        when(bookService.deleteBook(isbn)).thenThrow(new BookException("Book with ISBN " + isbn + " not found."));

        ResponseEntity<Object> result = bookController.deleteBook(isbn);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals(BookConstants.INTERNAL_SERVER_ERROR_MSG, result.getBody());
    }


    @Test
    void testAddReview() throws BookException {
        Book book = new Book();
        long isbn = 1234567890L;
        Map<String, String> payload = new HashMap<>();
        payload.put("review", "Great book!");

        when(bookService.addReview(isbn, payload.get("review"))).thenReturn(book);

        ResponseEntity<Object> response = bookController.addReview(isbn, payload);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    void testAddReview_Exception() throws BookException {
        long isbn = 987654321L;
        String review = "Invalid review";
        Map<String, String> payload = Collections.singletonMap("review", review);

        when(bookService.addReview(isbn, review)).thenThrow(new BookException("Error adding review"));

        ResponseEntity<Object> response = bookController.addReview(isbn, payload);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(BookConstants.INTERNAL_SERVER_ERROR_MSG, response.getBody());
    }

    @Test
    void testUpdateBook() throws BookException {

        long isbn = 123L;
        Book bookDetails = new Book();
        Book updatedBook = new Book();

        when(bookService.updateBook(isbn, bookDetails)).thenReturn(updatedBook);

        ResponseEntity<Object> result = bookController.updateBook(isbn, bookDetails);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedBook, result.getBody());
    }

    @Test
    void testUpdateBookException() throws BookException {
        // Arrange
        long isbn = 123L;
        Book bookDetails = new Book(); // Set up your book details here

        when(bookService.updateBook(isbn, bookDetails)).thenThrow(new BookException("Book with ISBN " + isbn + " not found."));

        ResponseEntity<Object> response = bookController.updateBook(isbn, bookDetails);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(BookConstants.INTERNAL_SERVER_ERROR_MSG, response.getBody());

    }


}
