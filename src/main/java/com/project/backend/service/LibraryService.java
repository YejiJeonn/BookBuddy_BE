package com.project.backend.service;

import com.project.backend.dto.LibraryRequestDto;
import com.project.backend.entity.Library;
import com.project.backend.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    // 도서 저장
    public Library saveBook(Long num, String userId, LibraryRequestDto request) {
        Library library = new Library();

        library.setLibrary(num, userId, request.getNickname(), request.getTitle(), request.getIsbn(), request.getComment());

        return libraryRepository.save(library);
    }

    // 도서 삭제
    public void deleteBook(Long num, String isbn) {
        libraryRepository.deleteByIdAndIsbn(num, isbn);
    }

    // 저장된 도서 조회
    public List<Library> getBooksById(Long id) {
        return libraryRepository.findAll().stream()
                .filter(book -> book.getId().equals(id)) // 괄호 닫기
                .toList(); // toList() 호출
    }

}
