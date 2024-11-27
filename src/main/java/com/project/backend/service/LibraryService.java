package com.project.backend.service;

import com.project.backend.dto.LibraryRequestDto;
import com.project.backend.entity.Library;
import com.project.backend.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    // 도서 저장
    public Library saveBook(Long num, LibraryRequestDto request) {
        Library library = new Library();

        library.setLibrary(num, request.getUserId(), request.getNickname(), request.getTitle(), request.getIsbn(), request.getAuthor(), request.getPublisher(), request.getPubDate(), request.getCover());
        System.out.println("isbn:: " + request.getIsbn());

        return libraryRepository.save(library);
    }

    // 도서 삭제
    public void deleteBook(Long num, String isbn) {
        libraryRepository.deleteByIdAndIsbn(num, isbn);
    }

    // 저장된 도서 조회
    public List<Library> getBooksById(Long id) {
        return libraryRepository.findAll().stream()
                .filter(book -> book.getNum().equals(id)) // 괄호 닫기
                .toList(); // toList() 호출
    }

    public Optional<Library> checkBooks(Long id, String isbn) {
        return libraryRepository.findByIdAndIsbn(id, isbn);
    }

}
