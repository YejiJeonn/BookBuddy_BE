package com.project.backend.repository;

import com.project.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    User findUserById(Long id);

//    LoginUserRequestDto findUserById(@Param("id") Long id);

    boolean existsByUserId(String userId);

    boolean existsByNickname(String nickName);
}
