package com.sparta.lv3backoffice.domain.entity;

import com.sparta.lv3backoffice.domain.dto.lecture.LectureRequestDto;
import com.sparta.lv3backoffice.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "lectures")
public class Lecture extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @Transient
    private String tutorName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Long price;

    // Lecture 엔티티에 대한 참조를 반환하는 메서드
    public Tutor getTutor() {
        return this.tutor;
    }

    public Lecture(LectureRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.tutorName = requestDto.getTutorName();
        this.description = requestDto.getDescription();
        this.category = requestDto.getCategory();
        this.price = requestDto.getPrice();
    }

    public void update(LectureRequestDto lectureRequestDto) {
        this.title = lectureRequestDto.getTitle();
        this.description = lectureRequestDto.getDescription();
        this.category = lectureRequestDto.getCategory();
        this.price = lectureRequestDto.getPrice();
    }

    public void setTutor(Tutor tutor) {
        // 지금 Lecture의 필드 tutorName에 Tutor 인스턴스의 tutorName을 할당하기
        if (tutor != null) {
            this.tutor = tutor;
            this.tutorName = tutor.getTutorName();
        } else {
            this.tutor = null;
            this.tutorName = null;
        }
    }
}