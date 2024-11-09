package com.project.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SearchService {

    @Value("${aladin.api.key}") // application.properties 파일에 저장한 API 키를 불러옴
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String searchBooks(String query, Integer maxResults, Integer start) {

        String url = String.format("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=%s&Query=%s&QueryType=Title&MaxResults=%d&start=%d&SearchTarget=Book&Cover=Big&output=JS&Version=20131101",
                apiKey, query, maxResults, start);
        System.out.println(url);

        // 알라딘 API에 GET 요청을 보냄
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // JSON 형태의 결과 반환
        return response.getBody();
    }
}
