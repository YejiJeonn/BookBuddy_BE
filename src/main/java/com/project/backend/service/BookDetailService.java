package com.project.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BookDetailService {

    @Value("${aladin.api.key}") // application.properties 파일에 저장한 API 키를 불러옴
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String searchBookDetail(String itemId) {

        String url = String.format("http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?ttbkey=%s&itemIdType=ISBN13&ItemId=%s&Cover=Big&output=JS&Version=20131101&OptResult=ratingInfo,bestSellerRank,authors,reviewList,Toc,Story,customerReviewRank",
                apiKey, itemId);
//        System.out.println(url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }
}
