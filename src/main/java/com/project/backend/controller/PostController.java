package com.project.backend.controller;

import com.project.backend.dto.PostRequestDto;
import com.project.backend.entity.Post;
import com.project.backend.entity.User;
import com.project.backend.repository.PostRepository;
import com.project.backend.repository.UserRepository;
import com.project.backend.security.TokenProvider;
import com.project.backend.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PostService postService;

    // 특정 ISBN에 해당하는 게시글 조회
    @GetMapping("/{bookIsbn}")
    public ResponseEntity<List<Post>> getPostsByIsbn(@PathVariable String bookIsbn) {

        // 공백 제거 및 대소문자 통일 처리
        String trimmedIsbn = bookIsbn.trim();
        System.out.println("book: " + bookIsbn);
        List<Post> posts = postRepository.findByIsbn(trimmedIsbn);

//        List<Post> posts = postRepository.findByIsbn(isbn);

        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto request, HttpServletRequest httpServletRequest) {
        System.out.println(request.getNickname());
        // JWT 토큰 검증
        String token = httpServletRequest.getHeader("Authorization").substring(7);

        Long id = tokenProvider.getSubject(token);
        User user = userRepository.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>("유효하지 않은 사용자", HttpStatus.UNAUTHORIZED);
        }

        if (request.getIsbn() == null || request.getIsbn().isEmpty()) {
            return new ResponseEntity<>("ISBN이 필요합니다.", HttpStatus.BAD_REQUEST);
        }

        postService.savePost(id, request);
        return new ResponseEntity<>("게시글이 저장되었습니다.", HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        // JWT 토큰 검증
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userId = tokenProvider.getSubject(token);
        Post post = postRepository.findById(id).orElse(null);

        if (post == null) {
            return new ResponseEntity<>("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        if (!post.getNum().equals(userId)) {
            return new ResponseEntity<>("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        postRepository.deleteById(id);
        return new ResponseEntity<>("게시글이 삭제되었습니다.", HttpStatus.OK);
    }

    // 게시글 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postDetails, HttpServletRequest httpServletRequest) {
        // JWT 토큰 검증
        String token = httpServletRequest.getHeader("Authorization").substring(7);
        Long userId = tokenProvider.getSubject(token);

        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return new ResponseEntity<>("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        if (!post.getUserId().equals(userId.toString())) {
            return new ResponseEntity<>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        post.updatePost(postDetails.getTitle(), postDetails.getContent());
        postRepository.save(post);

        return new ResponseEntity<>("게시글이 수정되었습니다.", HttpStatus.OK);
    }
}
