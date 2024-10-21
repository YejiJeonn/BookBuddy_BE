package com.project.backend.service;

import com.project.backend.dto.CreateUserRequestDto;
import com.project.backend.entity.User;
import com.project.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(CreateUserRequestDto dto) {

        User user = new User();
        user.setUser_id(dto.getUserId());
        user.setPw(dto.getPassword());
        user.setName(dto.getName());
        user.setBirth(dto.getBirth());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getNickName());

        userRepository.save(user);
        // jpa의 기본 기능 crud 기능 활용
        // jpa 레포지토리 상속 레포지토리는 기본 기능들 지원 (규약 지킬 경우)
    }
}
