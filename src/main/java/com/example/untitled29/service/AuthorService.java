package com.example.untitled29.service;

import com.example.untitled29.model.Author;
import com.example.untitled29.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAll() { return authorRepository.findAll(); }

    // Repository 차원에서 Optional 을 주었으니 여기서는 그냥 Author
    public Author getById(Integer id) {
        return authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("저자 없음"));
    }

    public Author create(Author author) {
        return authorRepository.save(author);
    }

    public Author update(Integer id, Author updatedAuthor) {
        getById(id);
        updatedAuthor.setId(id);

        return authorRepository.save(updatedAuthor);
    }

//    public Author update(Integer id, Author updatedAuthor) {
//        return authorRepository.update(id, updatedAuthor);
//    }

    public void delete(Integer id) {
        authorRepository.delete(id);
    }
}