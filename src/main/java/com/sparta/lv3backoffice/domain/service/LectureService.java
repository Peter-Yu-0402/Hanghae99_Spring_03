package com.sparta.lv3backoffice.domain.service;

import com.sparta.lv3backoffice.domain.dto.lecture.LectureRequestDto;
import com.sparta.lv3backoffice.domain.dto.lecture.LectureResponseDto;
import com.sparta.lv3backoffice.domain.entity.Lecture;
import com.sparta.lv3backoffice.domain.entity.Tutor;
import com.sparta.lv3backoffice.domain.repository.LectureRepository;
import com.sparta.lv3backoffice.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// 강의 관련 서비스
public class LectureService {
    private final LectureRepository lectureRepository;

    // 강의 등록
    @Transactional
    public LectureResponseDto registerLecture(LectureRequestDto requestDto) {
        // 강의 등록
        Lecture lecture = lectureRepository.save(requestDto.toEntity());

        return new LectureResponseDto(lecture);
    }

    // 선택한 강의 정보 수정
    @Transactional
    public LectureResponseDto updateLecture(Long lectureId, LectureRequestDto requestDto, HttpServletRequest res) {

        // 강의가 DB에 존재하는지 확인
        Lecture lecture = lectureRepository.findById(lectureId)
                            .orElseThrow(()-> new IllegalArgumentException("NOT FOUND LECTURE"));

        lecture.update(requestDto);
        return new LectureResponseDto(lecture);
    }

    // 선택 강의 조회
    @Transactional(readOnly = true)
    public LectureResponseDto getLecture(Long lectureId) {

        // 강의가 DB에 존재하는지 확인
        Lecture lecture = lectureRepository.findById(lectureId)
                            .orElseThrow(()-> new IllegalArgumentException("NOT FOUND LECTURE"));

        return new LectureResponseDto(lecture);
    }

    // 카테고리별 강의 목록 조회
    @Transactional
    public List<LectureResponseDto> getLecturesByCategory(String category) {

        List<Lecture> lecture = lectureRepository.findByCategoryOrderByCreatedAtDesc(category);
        // 해당 카테고리에 강의가 DB에 존재하는지 확인 후 반환
        return lecture.stream().map(LectureResponseDto::new).collect(Collectors.toList());
    }

    // 강사별 강의 목록 조회
    @Transactional
    public List<LectureResponseDto> getLectureByTutorId(Tutor tutorId) {

        List<Lecture> lecture = lectureRepository.findByTutorIdOrderByCreatedAtDesc(tutorId);
        // 해당 카테고리에 강의가 DB에 존재하는지 확인 후 반환
        return lecture.stream().map(LectureResponseDto::new).collect(Collectors.toList());
    }
}
