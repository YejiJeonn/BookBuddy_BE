package com.project.backend.controller;

import com.project.backend.service.BookDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookDetailController {

    private final BookDetailService bookDetailService;

    @GetMapping("/book-detail")
    public String getBookDetail(
            @RequestParam(value = "itemId") String itemId
    ) {
        System.out.println(itemId);
        return bookDetailService.searchBookDetail(itemId);
    }
}
