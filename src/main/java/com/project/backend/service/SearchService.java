package com.project.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    public String searchBooks(String query) {



        return "";
    }
}
