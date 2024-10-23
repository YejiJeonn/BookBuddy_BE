package com.project.backend.service;

import com.project.backend.dto.CreateUserRequestDto;
import com.project.backend.dto.LoginUserRequestDto;
import com.project.backend.entity.User;
import com.project.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 비즈니스 로직을 처리하는 계층
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입 시 정보 저장 로직
    public void saveUser(CreateUserRequestDto dto) {

        User user = new User();
        user.setUserId(dto.getUserId());
        user.setPw(dto.getPassword());
        user.setName(dto.getName());
        user.setBirth(dto.getBirth());
        user.setEmail(dto.getEmail());
        user.setNickName(dto.getNickName());

        userRepository.save(user);
        //userRepository.save(user.toEntity(encoder.encode(user.getPw())));
        // jpa의 기본 기능 crud 기능 활용
        // jpa 레포지토리 상속 레포지토리는 기본 기능들 지원 (규약 지킬 경우)
    }

    // 로그인 시 기존 정보 확인 로직
    public boolean loginUser(LoginUserRequestDto dto) {

        User user = userRepository.findByUserId(dto.getUserId()).orElse(null);

        if(user == null){
            return false;
        }

        return user != null && user.getPw().equals(dto.getPassword());
    }

    // 정보 불러오기
    public User infoUser(LoginUserRequestDto dto) {
        User user = userRepository.findByUserId(dto.getUserId()).orElse(null);

        return user;
    }

    // 회원가입 시 아이디 중복 확인 로직
    public boolean checkIdDuplicate(String userId){

        return userRepository.existsByUserId(userId);
    }

    // 회원가입 시 닉네임 중복 확인 로직
    public boolean checkNickNameDuplicate(String nickName){

        return userRepository.existsByNickName(nickName);
    }

    // 회원가입 시 입력한 비밀번호 확인 로직
    public boolean checkPw(String pw, String checkPassword){

        return pw.equals(checkPassword);
    }
}
