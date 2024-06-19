package com.book.store.service;



import com.book.store.exception.BookException;
import com.book.store.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks()throws BookException;

    public String saveBook(List<Book> book)throws BookException;

    public Book getBookByIsbn(long isbn)throws BookException;

    public Book updateBook(long isbn, Book bookDetails)throws BookException;

    public String deleteBook(long isbn)throws BookException;

    public Book addReview(long isbn, String review)throws BookException;


}
