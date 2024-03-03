package com.sparta.lv3backoffice.domain.dto.tutor;

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
        return Tutor.builder()
                .tutorName(tutorName)
                .experienceYears(experienceYears)
                .company(company)
                .phoneNumber(phoneNumber)
                .bio(bio)
                .build();
    }
    public void updateTutor(Tutor tutor) {
        this.tutorName = tutor.getTutorName();
        this.experienceYears = tutor.getExperienceYears();
        this.company = tutor.getCompany();
        this.phoneNumber = tutor.getPhoneNumber();
        this.bio = tutor.getBio();
    }
}
