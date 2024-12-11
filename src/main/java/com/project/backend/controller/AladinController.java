package com.project.backend.controller;

import com.project.backend.service.AladinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AladinController {

    private final AladinService aladinService;

    @GetMapping("/aladin-books")
    public String getAladinProducts(
            @RequestParam String queryType,
            @RequestParam int maxResults,
            @RequestParam int start
    ) {
        return aladinService.searchProducts(queryType, maxResults, start);
    }
    
}
