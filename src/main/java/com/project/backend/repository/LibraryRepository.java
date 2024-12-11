package com.project.backend.repository;

import com.project.backend.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    Optional<Library> findByIdAndIsbn(Long num, String isbn);

    @Modifying
    @Query("DELETE FROM Library l WHERE l.num = :num AND l.isbn = :isbn")
    void deleteByIdAndIsbn(Long num, String isbn);
}
