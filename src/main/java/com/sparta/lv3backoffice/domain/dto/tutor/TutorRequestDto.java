package com.sparta.lv3backoffice.domain.dto.tutor;

import com.sparta.lv3backoffice.domain.entity.Lecture;
import com.sparta.lv3backoffice.domain.entity.Tutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TutorRequestDto {
    private String tutorName;
    private Long experienceYears;
    private String company;
    private String phoneNumber;
    private String bio;

    public Tutor toEntity() {
        Tutor tutor = new Tutor();
        Lecture lecture = new Lecture();
        tutor.setTutor(experienceYears, company, phoneNumber, bio);
        tutor.addLectureList(lecture);
        return tutor;

//        return Tutor.builder()
//                .tutorName(tutorName)
//                .experienceYears(experienceYears)
//                .company(company)
//                .phoneNumber(phoneNumber)
//                .bio(bio)
//                .build();
    }


}
