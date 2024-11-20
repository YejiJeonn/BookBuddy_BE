package com.project.backend.repository;

import com.project.backend.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    Optional<Library> findByIdAndIsbn(Long num, String isbn);

    void deleteByIdAndIsbn(Long num, String isbn);
}
