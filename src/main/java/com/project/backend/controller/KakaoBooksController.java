package com.project.backend.controller;

import com.project.backend.service.KakaoBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KakaoBooksController {

    @Autowired
    private KakaoBookService kakaoBookService;

    @GetMapping("/search-books")
    public String searchBooks(@RequestParam String query){
        return kakaoBookService.searchBooks(query);
    }
}
