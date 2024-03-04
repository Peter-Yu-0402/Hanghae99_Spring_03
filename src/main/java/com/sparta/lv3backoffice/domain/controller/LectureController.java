package com.sparta.lv3backoffice.domain.controller;

import com.sparta.lv3backoffice.domain.dto.lecture.LectureRequestDto;
import com.sparta.lv3backoffice.domain.dto.lecture.LectureResponseDto;
import com.sparta.lv3backoffice.domain.dto.tutor.TutorRequestDto;
import com.sparta.lv3backoffice.domain.entity.Lecture;
import com.sparta.lv3backoffice.domain.entity.User;
import com.sparta.lv3backoffice.domain.entity.UserRoleEnum;
import com.sparta.lv3backoffice.domain.service.LectureService;
import com.sparta.lv3backoffice.global.exception.NotFoundException;
import com.sparta.lv3backoffice.global.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

// 강의 관련 컨트롤러
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {
    private final LectureService lectureService;

    // 강의 등록
    @PostMapping("/lecture")
    public ResponseEntity<?> registerLecture(@RequestBody LectureRequestDto requestDto) {
        return handleRequest(() -> {
            LectureResponseDto responseDto = lectureService.registerLecture(requestDto);
            return ResponseEntity.ok(responseDto);
        });
    }

    // 선택한 강의 정보 수정
    @PutMapping("/lecture/{lectureId}")
    public ResponseEntity<?> updateLecture(@PathVariable Long lectureId, @RequestBody LectureRequestDto requestDto, HttpServletRequest res) {

        User user = (User) res.getAttribute("user");
        if (user.getRole() != UserRoleEnum.MANAGER) {
            throw new UnauthorizedException("사용자는 Manager 권한이 필요합니다.");
        }

        return handleRequest(() -> {
            LectureResponseDto responseDto = lectureService.updateLecture(lectureId, requestDto, res);
            return ResponseEntity.ok(responseDto);
        });
    }

    // 선택 강의 조회
    @GetMapping("/lecture/{lectureId}")
    public ResponseEntity<?> getLecture(@PathVariable Long lectureId) {
        return handleRequest(() -> {
            LectureResponseDto responseDto = lectureService.getLecture(lectureId);
            return ResponseEntity.ok(responseDto);
        });
    }

    // 카테고리별 강의 목록 조회
    @GetMapping("/lecture/category/{category}")
    public ResponseEntity<?> getLectureByCategory(@PathVariable String category) {
        return handleRequest(() -> {
            List<LectureResponseDto> responseDto = lectureService.getLecturesByCategory(category);
            return ResponseEntity.ok(responseDto);
        });
    }

    private ResponseEntity<?> handleRequest(Supplier<ResponseEntity<?>> supplier) {
        try {
            return supplier.get();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("인터넷 서버 오류: " + e.getMessage());
        }
    }
}
