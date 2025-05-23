package com.project.backend.controller;

import com.project.backend.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    public String searchBooks(
            @RequestParam String query,
            @RequestParam int maxResults,
            @RequestParam int start
    ) {
        return searchService.searchBooks(query, maxResults, start);
    }
}
