package com.example.untitled29.controller;

import com.example.untitled29.dto.AuthorDto;
import com.example.untitled29.model.Author;
import com.example.untitled29.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public Author create(@Valid @RequestBody AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());

        return authorService.create(author);
    }

}