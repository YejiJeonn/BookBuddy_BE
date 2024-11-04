package com.project.backend.controller;

import com.project.backend.service.KakaoBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class KakaoBooksController {

    private final KakaoBookService kakaoBookService;

    @GetMapping("/search-books")
    public String searchBooks(@RequestParam String query) {
        return kakaoBookService.searchBooks(query);
    }
}
