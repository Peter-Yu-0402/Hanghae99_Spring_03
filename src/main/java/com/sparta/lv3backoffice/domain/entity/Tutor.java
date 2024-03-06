package com.sparta.lv3backoffice.domain.entity;

import com.sparta.lv3backoffice.domain.dto.tutor.TutorRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List <Lecture> lectureList = new ArrayList<>();

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

    public Tutor(TutorRequestDto requestDto) {
        this.tutorName = requestDto.getTutorName();
        this.bio = requestDto.getBio();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.experienceYears = requestDto.getExperienceYears();
    }


    public void addLectureList(Lecture lecture) {
        this.lectureList.add(lecture);
        lecture.setTutor(this); // 외래 키 연관관계 설정
    }


    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public Long getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Long experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void update(TutorRequestDto requestDto) {
        this.experienceYears = requestDto.getExperienceYears();
        this.company =requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.bio = requestDto.getBio();
    }

    public void setTutor(Long experienceYears, String company, String phoneNumber, String bio) {
        this.experienceYears = experienceYears;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
    }
}