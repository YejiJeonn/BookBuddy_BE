package com.project.backend.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {

    private Long num;
    private String userId;
    private String title;
    private String content;
    private String nickname;
    private String isbn;
}
