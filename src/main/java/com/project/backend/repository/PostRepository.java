package com.project.backend.repository;

import com.project.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByIsbn(String Isbn);

    // 최신 글을 가져오는 메소드 (도서의 ISBN을 기준으로 필터링)
    @Query("SELECT p FROM Post p WHERE p.isbn = :isbn order by p.createdAt DESC")
    List<Post> findTopByIsbnOrderByCreateAtDesc(String isbn, org.springframework.data.domain.Pageable pageable);
}
