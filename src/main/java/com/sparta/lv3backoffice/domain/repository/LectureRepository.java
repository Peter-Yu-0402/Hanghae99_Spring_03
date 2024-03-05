package com.sparta.lv3backoffice.domain.repository;

import com.sparta.lv3backoffice.domain.entity.Lecture;
import com.sparta.lv3backoffice.domain.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository <Lecture, Long>{
    List<Lecture> findByCategoryOrderByCreatedAtDesc(String category);
    List<Lecture> findByTutorIdOrderByCreatedAtDesc(Tutor tutorId);

}
