package com.example.untitled29.controller;

import com.example.untitled29.dto.BookDto;
import com.example.untitled29.model.Book;
import com.example.untitled29.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> list() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    @PostMapping
    public Book create(@Valid @RequestBody BookDto bookDto) {
        Book book = new Book();                  // 새 Book 을 만들고
        book.setTitle(bookDto.getTitle());       // 요소들을 넣는다.
        book.setAuthorId(bookDto.getAuthorId()); // 요소들을 넣는다.

        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Integer id, @Valid @RequestBody BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());          // 타이틀 바꾸기
        book.setAuthorId(bookDto.getAuthorId());       // 작가 바꾸기

        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bookService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
