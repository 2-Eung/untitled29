package com.example.untitled29.controller;

import com.example.untitled29.model.Author;
import com.example.untitled29.service.AuthorService;
import com.example.untitled29.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DashboardController {
    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping("/api/dashboard/authors")
    public Map<Author, Long> countByAuthor() {          // 작가 가 책을 몇권 썻는지 기록
        return authorService.getAll().stream()
                .collect(                               // 작가 조회
                        Collectors.toMap(               // 작가의 책 조회
                                author -> author,
                                author -> bookService.getAll().stream()
                                        .filter(book -> book.getAuthorId().equals(author.getId()))
                                        .count()        // 작가 이름이랑 Id 랑같은치 확인 후 맞으면 카운트한다.
                        )
                );
    }
}