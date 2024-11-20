package com.project.backend.controller;

import com.project.backend.dto.LibraryRequestDto;
import com.project.backend.entity.Library;
import com.project.backend.entity.User;
import com.project.backend.repository.LibraryRepository;
import com.project.backend.repository.UserRepository;
import com.project.backend.security.TokenProvider;
import com.project.backend.service.LibraryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class MyLibraryController {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final LibraryService libraryService;
    private final LibraryRepository libraryRepository;

    @PostMapping("/save-books")
    public ResponseEntity<?> saveBooks(@RequestBody LibraryRequestDto request, HttpServletRequest httpServletRequest) {

        // JWT 토큰 검증
        String token = httpServletRequest.getHeader("Authorization").substring(7);

        if (token == null) {
            return new ResponseEntity<>("로그인 해주세요.", HttpStatus.UNAUTHORIZED);
        }

        Long id = tokenProvider.getSubject(token);
        User user = userRepository.findUserById(id);
        String userId = user.getUserId();

        if (user == null) {
            return new ResponseEntity<>("유효하지 않은 사용자", HttpStatus.UNAUTHORIZED);
        }

        libraryService.saveBook(id, userId, request);

        return new ResponseEntity<>("내 서재에 저장되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/delete-book/{userId}/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long num, @PathVariable String isbn) {
        libraryService.deleteBook(num, isbn);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/book/{Id}")
    public ResponseEntity<List<Library>> getBooksById(@PathVariable Long num) {
        List<Library> books = libraryService.getBooksById(num);
        return ResponseEntity.ok(books);
    }
}
