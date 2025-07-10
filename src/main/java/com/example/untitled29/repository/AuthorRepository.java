package com.example.untitled29.repository;

import com.example.untitled29.model.Author;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AuthorRepository {
    // Integer 를 순서대로 넣어서 데이터베이스 에서의 Id 같은 역할을 만든다
    private final Map<Integer, Author> store = new LinkedHashMap<>();

    // 원자성을 보장해서 유니크함을 갖게 한다.
    private final AtomicInteger seq = new AtomicInteger(0);

    public Author save(Author author) {
        if (author.getId() == null) {
            author.setId(seq.incrementAndGet()); // 1 을 증가시켜 원자성 부여?
        }
        store.put(author.getId(), author);

        return author;
    }
}
