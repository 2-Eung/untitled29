package com.example.untitled29.repository;

// CRUD create road update delete

import com.example.untitled29.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class BookRepository {
    private final Map<Integer, Book> store = new LinkedHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    // null 값이 들어올수 있으니 Optional , service 에서도 예외처리해야한다
    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    public Book save(Book book) {
        if(book.getId() == null) {              // Id 가 없으면 생성
            book.setId(seq.incrementAndGet());
        }                                       // Create 와 Update 기능을 둘 다 갖음

        store.put(book.getId(), book);          // ID 가 있으면 put (덮어쓰기)

        return book;
    }

//    public Book update(Integer id, Book updatedBook) {
//        if (!store.containsKey(id)) {
//            throw new NoSuchElementException(id + "의 책이 없습니다");
//        }
//
//        updatedBook.setId(id);
//        store.put(id, updatedBook);
//
//        return updatedBook;
//    }

    public void delete(Integer id) {
        store.remove(id);
    }
}
