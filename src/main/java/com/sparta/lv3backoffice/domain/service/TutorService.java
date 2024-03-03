package com.sparta.lv3backoffice.domain.service;

import com.sparta.lv3backoffice.domain.dto.tutor.TutorRequestDto;
import com.sparta.lv3backoffice.domain.dto.tutor.TutorResponseDto;
import com.sparta.lv3backoffice.domain.entity.Lecture;
import com.sparta.lv3backoffice.domain.entity.Tutor;
import com.sparta.lv3backoffice.domain.entity.UserRoleEnum;
import com.sparta.lv3backoffice.domain.repository.LectureRepository;
import com.sparta.lv3backoffice.domain.repository.TutorRepository;
import com.sparta.lv3backoffice.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.List;


// 강사 관련 서비스
@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;

    private final String MANAGER_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    // 강사 등록
    public TutorResponseDto registerTutor(TutorRequestDto requestDto, @CookieValue(JwtUtil.AUTHORIZATION_KEY) String token) {

        // 강사 등록
        // TutorRequestDto로 Entity를 만들어 TutorRepository에 저장하고 Tutor 타입의 참조변수 tutor에 할당한다.
        Tutor tutor = tutorRepository.save(requestDto.toEntity());
        // tutor를 TutorResponseDto 생성자에 담아 인스턴스를 만든다.
        return new TutorResponseDto(tutor);
    }

    @Transactional
    // 선택한 강사 정보 수정
    public TutorResponseDto updateTutor(long tutorId, TutorRequestDto requestDto, @CookieValue(JwtUtil.AUTHORIZATION_KEY) String token) {
        // 강사가 DB 에 존재하는지 확인
        Tutor tutor = tutorRepository.findByTutorId(tutorId)
                        .orElseThrow(()-> new IllegalArgumentException("NOT FOUND TUTOR ID"));

        tutor.update(requestDto);
        return new TutorResponseDto(tutor);
    }

    @Transactional(readOnly = true)
    // 선택 강사 조회
    public TutorResponseDto getTutor(Long tutorId, @CookieValue(JwtUtil.AUTHORIZATION_KEY) String token) {

        // 강사가 DB 에 존재하는지 확인
        Tutor tutor = tutorRepository.findByTutorId(tutorId)
                        .orElseThrow(()-> new IllegalArgumentException("NOT FOUND TUTOR ID"));

        return new TutorResponseDto(tutor);
    }
}

