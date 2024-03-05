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

    // 다대일 양방향 관계. 서로 참조할 수 있기 때문에 양방향. 강의를 중복 소유할 수 없기 때문에 다대일.
    @ManyToOne(fetch = FetchType.LAZY)
    /// 참조하는 컬럼명
    @JoinColumn(name = "tutor_id")
    private Tutor tutorId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Long price;

    public Lecture(Tutor tutor, String title, String description, String category, Long price) {
        this.tutorName = tutor.getTutorName();
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public void update(LectureRequestDto lectureRequestDto) {
        this.tutorName = lectureRequestDto.getTutorName();
        this.title = lectureRequestDto.getTitle();
        this.description = lectureRequestDto.getDescription();
        this.category = lectureRequestDto.getCategory();
        this.price = lectureRequestDto.getPrice();
    }
}
