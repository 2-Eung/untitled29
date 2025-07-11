package com.example.untitled29.controller;

import com.example.untitled29.dto.AuthorDto;
import com.example.untitled29.model.Author;
import com.example.untitled29.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public List<Author> list() {
        return authorService.getAll();
    }
//    @GetMapping
//    public ResponseEntity<List<Author>> list() {
//        List<Author> authors = authorService.getAll();
//
//        return ResponseEntity.ok(authors);
//    } // 원래라면 이런 형식인데 Author 를 사용해도 자동으로 바꿔준다. (.ok 는 간략한정보)

    // 단일 조회에 대한 내용
    @GetMapping("/{id}")                            // 동적경로
    public Author get(@PathVariable Integer id) {   // 동적경로에 필요한 값은 여기서 꺼내쓴다
        return authorService.getById(id);           // 서비스로 전달
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Author> get(@PathVariable Integer id) {
//        Author author = authorService.getById(id);
//
//        return ResponseEntity.ok(author);
//    } // 원래라면 이런 형식인데 Author 를 사용해도 자동으로 바꿔준다. (.ok 는 간략한정보)

    @PostMapping
    public Author create(@Valid @RequestBody AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());

        return authorService.create(author);
    }
//    @PostMapping
//    public ResponseEntity<Author> create(@Valid @RequestBody AuthorDto authorDto) {
//        Author author = new Author();
//        author.setName(authorDto.getName());
//
//        Author saved = authorService.create(author);
//
//        return ResponseEntity.created(URI.create("/api/authors/" + saved.getId())).body(saved);
//    } // 원래라면 이런 형식인데 Author 를 사용해도 자동으로 바꿔준다. (.created 는 보다 자세한 정보)

    // 주소값에 아이디를 받아온다
    // Postman 에 바꾸려는 값도 입력해주어야 한다.
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(
            @PathVariable Integer id,
            @Valid @RequestBody AuthorDto authorDto
    ) {
        Author author = new Author();
        author.setName(authorDto.getName());

        Author updated = authorService.update(id, author);

        return ResponseEntity.ok(updated);
    } // .ok 는 간략한정보

    // REST API 에서 맵핑 앞에 있는 GET POST DELETE 등은 포스트맨에서 고르는거랑 같다
    // REST API 는 응답?을 하는것이다 그래서 당연스럽게 응답을 처리하는 Response 가 와야한다
    // 그러나 위에서 Author , List<> 를 쓴 이유는 SpringBoot 내에 자동으로 Response 로 바꿔주는 기능이 있기 때문
    // 하지만 이번에는 반환값이 없기 때문에 ResponseEntity<void> 를 적어줘야한다 (와일드카드 '?' 도 가능)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        authorService.delete(id);

        return ResponseEntity.noContent().build();
    }
}