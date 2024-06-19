package com.book.store.service;


import com.book.store.exception.BookException;
import com.book.store.model.Author;
import com.book.store.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;



    public Author getAuthor(long authorId) throws BookException {
        return authorRepository.findById(authorId).orElseThrow(() -> new BookException("Author not found"));
    }
}
