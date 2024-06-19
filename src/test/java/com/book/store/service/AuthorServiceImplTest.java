package com.book.store.service;

import com.book.store.exception.BookException;
import com.book.store.model.Author;
import com.book.store.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAuthor_ExistingAuthor() throws BookException {
        long authorId = 123L;
        Author existingAuthor = new Author(authorId, "James Cameron");

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(existingAuthor));

        Author result = authorService.getAuthor(authorId);

        assertThat(result).isEqualTo(existingAuthor);
    }
}