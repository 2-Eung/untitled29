package com.example.untitled29.repository;

import com.example.untitled29.model.Author;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AuthorRepository {
    // Integer 를 순서대로 넣어서 데이터베이스 에서의 Id 같은 역할을 만든다
    private final Map<Integer, Author> store = new LinkedHashMap<>();

    // 원자성을 보장해서 유니크함을 갖게 한다.
    private final AtomicInteger seq = new AtomicInteger(0);

    // 리스트로 변환해서 출력하겠다.
    public List<Author> findAll() {
        return new ArrayList<>(store.values());
    }

    // 단일조회 (없을수도 있어서 null 값을 받기위해 Optional 을 사용)
    public Optional<Author> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    public Author save(Author author) {
        if (author.getId() == null) {
            author.setId(seq.incrementAndGet()); // 1 을 증가시켜 원자성 부여?
        }
        store.put(author.getId(), author);

        return author;
    }

//    public Author update(Integer id, Author updatedAuthor) {
//        if (!store.containsKey(id)) {               // 업데이트를 하려면 해당 값이 있는지 확인해야한다.
//            throw new NoSuchElementException(id +"의 저자가 없습니다");
//        }
//
//        updatedAuthor.setId(id);                    // Id 는 유지하고
//        store.put(id, updatedAuthor);               // 이름은 바꾼다 (.put 으로인해 덮어씌워진다)
//
//        return updatedAuthor;
//    } // 근데 save 가 update 기능도 수행중이라 이건 없어도 될것같다.

    // 삭제를 하면 반환값이 없다 그래서 void
    // 만약 삭제한 데이터를 모아두고싶으면 void 말고 다른거
    // Map 에는 .remove(키값) 이있는데 키값을 넣으면 삭제를 한다.
    public void delete(Integer id) {
        store.remove(id);
    }


}
