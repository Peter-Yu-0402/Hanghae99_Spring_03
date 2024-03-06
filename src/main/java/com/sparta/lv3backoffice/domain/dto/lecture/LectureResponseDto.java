package com.sparta.lv3backoffice.domain.dto.lecture;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.lv3backoffice.domain.entity.Lecture;
import com.sparta.lv3backoffice.domain.entity.Tutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponseDto {
    private Long id;
    private Long tutorId;
    private String tutorName;
    private String title;
    private String description;
    private String category;
    private Long price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public LectureResponseDto(Lecture lecture) {
        this.id = lecture.getLectureId();
        this.tutorId = lecture.getTutor().getTutorId();
        this.tutorName = lecture.getTutor().getTutorName();
        this.title = lecture.getTitle();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.price = lecture.getPrice();
        this.createdAt = lecture.getCreatedAt();

    }
}
