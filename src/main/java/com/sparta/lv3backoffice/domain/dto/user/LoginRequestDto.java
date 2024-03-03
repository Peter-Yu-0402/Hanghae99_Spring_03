package com.sparta.lv3backoffice.domain.dto.user;

import com.sparta.lv3backoffice.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


// 회원정보UserInfo = 로그인 정보 : 로그인, 비밀번호
@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDto {
    private String email;
    private String password;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}