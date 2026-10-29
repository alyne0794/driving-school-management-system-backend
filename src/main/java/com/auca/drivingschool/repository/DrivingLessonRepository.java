package com.auca.drivingschool.repository;

import com.auca.drivingschool.model.DrivingLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DrivingLessonRepository extends JpaRepository<DrivingLesson, UUID> {
}