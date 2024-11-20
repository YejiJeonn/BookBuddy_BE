package com.project.backend.service;

import com.project.backend.dto.PostRequestDto;
import com.project.backend.entity.Post;
import com.project.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void savePost(Long id, PostRequestDto request) {
        Post post = new Post();

        post.setPost(id, request.getUserId(), request.getTitle(), request.getContent(), request.getNickname(), request.getIsbn());

        System.out.println(post.getNickname());
        postRepository.save(post);
    }

}
