package com.project.backend.repository;

import com.project.backend.dto.LoginUserRequestDto;
import com.project.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    LoginUserRequestDto findUserById(@Param("id") Long id);

    boolean existsByUserId(String userId);

    boolean existsByNickname(String nickName);
}
