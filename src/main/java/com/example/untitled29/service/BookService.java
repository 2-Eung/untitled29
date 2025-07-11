package com.example.untitled29.service;

import com.example.untitled29.model.Book;
import com.example.untitled29.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("책 없음"));
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Integer id, Book book) {
        getById(id);             // 아이디 값을 넣어서 값이 (책이) 있으면 통과 값이 (책이) 없으면 에러가 뜬다
        book.setId(id);          // 통과하면 실행

        return bookRepository.save(book);
    } // Repository 에서 update 삭제 후 코드

//    public Book update(Integer id, Book book) {
//        return bookRepository.update(id, book);
//    } // Repository 에서 update 삭제 전 코드

    public void delete(Integer id) {
        bookRepository.delete(id);
    }
}
