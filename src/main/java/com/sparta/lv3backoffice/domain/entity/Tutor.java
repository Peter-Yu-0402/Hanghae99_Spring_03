package com.sparta.lv3backoffice.domain.entity;

import com.sparta.lv3backoffice.domain.dto.tutor.TutorRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tutors")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tutorId;

    // 컬럼명이 아니라 자바 변수명
    @OneToMany(mappedBy = "tutorId", fetch = FetchType.LAZY)
    private List<Lecture> lecture = new ArrayList<>();

    @Column(nullable = false)
    private String tutorName;

    @Column(nullable = false)
    private Long experienceYears;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String bio;


    public void update(TutorRequestDto requestDto) {
        this.tutorName = requestDto.getTutorName();
        this.experienceYears = requestDto.getExperienceYears();
        this.company =requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.bio = requestDto.getBio();
    }
}
