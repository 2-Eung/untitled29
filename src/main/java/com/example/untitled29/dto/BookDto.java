package com.example.untitled29.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Integer id;

    @NotBlank(message = "제목을 입력하세요.") // 문자열이 공백 인지 "" 확인
    private String title;

    @NotNull(message = "저자 ID를 지정하세요") // 값 자체가 Null 인지 확인
    private Integer authorId;
}