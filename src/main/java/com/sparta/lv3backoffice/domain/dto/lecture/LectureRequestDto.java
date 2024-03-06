
package com.sparta.lv3backoffice.domain.dto.lecture;

import com.sparta.lv3backoffice.domain.entity.Lecture;
import com.sparta.lv3backoffice.domain.entity.Tutor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureRequestDto {
    private String tutorName;
    private String title;
    private String description;
    private String category;
    private Long price;





//    public static Lecture toEntity(Tutor tutor) {
//        return Lecture.builder().build()
//                .tutorId(tutorId);
//    }


//    public Lecture toEntity() {
//        Lecture lecture = new Lecture();
//        Tutor tutor = new Tutor();
//        lecture.setTutor(tutor);
//        lecture.setTitle(this.title);
//        lecture.setDescription(this.description);
//        lecture.setCategory(this.category);
//        lecture.setPrice(this.price);
//        return lecture;

//        return Lecture.builder()
//                .tutor(tutor)
//                .tutorName()
//                .title(title)
//                .description(description)
//                .category(category)
//                .price(price)
//                .build();




}
