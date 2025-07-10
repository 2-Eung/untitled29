package com.example.untitled29.service;

import com.example.untitled29.model.Author;
import com.example.untitled29.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author create(Author author) {
        return authorRepository.save(author);
    }
}