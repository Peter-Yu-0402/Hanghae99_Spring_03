package com.sparta.lv3backoffice.domain.repository;

import com.sparta.lv3backoffice.domain.entity.Lecture;
import com.sparta.lv3backoffice.domain.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository <Tutor, Long> {
    Optional<Tutor> findByTutorId(Long tutorId);
}
