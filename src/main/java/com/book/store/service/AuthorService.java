package com.book.store.service;


import com.book.store.exception.BookException;
import com.book.store.model.Author;

public interface AuthorService {

    public Author getAuthor(long authorId) throws BookException;
}
