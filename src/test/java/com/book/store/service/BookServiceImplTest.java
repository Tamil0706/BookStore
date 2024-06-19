package com.book.store.service;


import com.book.store.exception.BookException;
import com.book.store.model.Book;
import com.book.store.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

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

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        assertEquals(123L, result.get(0).getIsbn());
        assertEquals(456L, result.get(1).getIsbn());
    }



    @Test
     void testSaveBook() throws BookException {
        Book book1 = new Book();
        book1.setIsbn(123L);
        Book book2 = new Book();
        book2.setIsbn(456L);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookRepository.saveAll(books)).thenReturn(books);

        String result = bookService.saveBook(books);

        assertEquals("Save", result);
    }

    @Test
     void testSaveBookException() {

        List<Book> emptyBookList = Collections.emptyList();

        try {
            bookService.saveBook(emptyBookList);
            fail("Expected BookException was not thrown.");
        } catch (BookException exception) {
            assertThrows(BookException.class, () -> bookService.saveBook(emptyBookList));
        }
    }

    @Test
     void testGetBookByIsbn() throws BookException {
        Book book = new Book();
        book.setIsbn(123L);

        when(bookRepository.findById(123L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookByIsbn(123L);

        assertEquals(123L, result.getIsbn());
    }

    @Test
     void testGetBookByIsbnException() throws BookException {
        long isbn = 124L;

        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        assertThrows(BookException.class, () -> bookService.getBookByIsbn(isbn));
    }

    @Test
     void testUpdateBook() throws BookException {
        long isbn = 123L;
        Book existingBook = new Book();
        existingBook.setIsbn(isbn);
        existingBook.setBookName("Old Book Name");

        Book newBookDetails = new Book();
        newBookDetails.setBookName("New Book Name");

        when(bookRepository.findById(isbn)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book result = bookService.updateBook(isbn, newBookDetails);

        assertEquals(isbn, result.getIsbn());
        assertEquals("New Book Name", result.getBookName());
    }

    @Test
     void testUpdateBookException() throws BookException {
        long isbn = 123L;
        Book newBookDetails = new Book();
        newBookDetails.setBookName("New Book Name");

        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        assertThrows(BookException.class, () -> bookService.updateBook(isbn, newBookDetails));
    }


    @Test
     void testDeleteBook() throws BookException {
        long isbn = 123L;
        Book book = new Book();
        book.setIsbn(isbn);

        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));

        String result = bookService.deleteBook(isbn);

        assertEquals("Book with ISBN :"+isbn+" was deleted.", bookService.deleteBook(isbn));
        assertEquals("Book with ISBN :"+isbn+" was deleted.", result);
    }



    @Test
     void testAddReview() throws BookException {
        long isbn = 1234567890L;
        Map<String, String> payload = new HashMap<>();
        payload.put("review", "Great book!");

        Book book = new Book();
        book.setIsbn(isbn);
        book.setReview(payload.get("review"));

        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.addReview(isbn, payload.get("review"));

        assertEquals(book, result);
    }

    @Test
     void testAddReviewException() throws BookException {
        long isbn = 1234567890L;
        Map<String, String> payload = new HashMap<>();
        payload.put("review", "Great book!");

        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        assertThrows(BookException.class, () -> bookService.addReview(isbn, payload.get("review")));
    }

}
