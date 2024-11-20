package com.project.backend.dto;

import lombok.Getter;

@Getter
public class LibraryRequestDto {

    private Long num;
    private String userId;
    private String nickname;
    private String title;
    private String isbn;
    private String comment;
}
