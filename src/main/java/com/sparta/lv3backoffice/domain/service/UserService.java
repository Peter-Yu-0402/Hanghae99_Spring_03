package com.sparta.lv3backoffice.domain.service;

import com.sparta.lv3backoffice.domain.dto.user.SignupRequestDto;
import com.sparta.lv3backoffice.domain.dto.user.SignupResponseDto;
import com.sparta.lv3backoffice.domain.entity.Department;
import com.sparta.lv3backoffice.domain.entity.User;
import com.sparta.lv3backoffice.domain.entity.UserRoleEnum;
import com.sparta.lv3backoffice.domain.repository.UserRepository;
import com.sparta.lv3backoffice.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN : 관리자로 회원가입 권한 부여
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 회원 가입
    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // email 중복확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 관리자 department 부서로 ROLE 확인 (권한확인)
        UserRoleEnum role = UserRoleEnum.STAFF;
        if (!requestDto.getDepartment().equals(Department.MARKETING)) {
            role = UserRoleEnum.MANAGER;  // 위에서 USER -> ADMIN 권한으로 덮어짐.
        }

        // 사용자 등록
        User user = userRepository.save(requestDto.toEntity(role, password)); // 등록하려면 user entity 클래스 객체를 만듦 : JPA 에서 Entity class 객체 하나가 DB의 한 열과 같다. (안의 내용은 생성자) 생성자를 통해서 만듦. 빨간 밑줄 뜨면 Create Constructor ^^

        return new SignupResponseDto(user);
    }
}