package com.auca.drivingschool.repository;

import com.auca.drivingschool.model.DrivingLesson;
import com.auca.drivingschool.model.LessonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DrivingLessonRepository extends JpaRepository<DrivingLesson, UUID> {

    List<DrivingLesson> findByStatusOrderByLessonDateAsc(LessonStatus status);

    List<DrivingLesson> findByLessonDateBetweenOrderByLessonDateAsc(LocalDate start, LocalDate end);
}