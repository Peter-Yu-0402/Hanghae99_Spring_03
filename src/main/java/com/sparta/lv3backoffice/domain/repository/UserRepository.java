package com.sparta.lv3backoffice.domain.repository;


import com.sparta.lv3backoffice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@EnableJpaRepositories
// 관리자 리포지토리 // UserService.java 에서 회원 중복 확인 부분
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
