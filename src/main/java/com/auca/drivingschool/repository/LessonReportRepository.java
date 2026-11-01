package com.auca.drivingschool.repository;

import com.auca.drivingschool.model.DrivingLesson;
import com.auca.drivingschool.model.LessonReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface LessonReportRepository extends JpaRepository<LessonReport, UUID> {
    @Query("SELECT r FROM LessonReport r WHERE r.drivingLesson.learner.enrollmentNumber = :enrollmentNumber ORDER BY r.recordedDate DESC")
    List<LessonReport> findByLearnerEnrollmentNumber(@Param("enrollmentNumber") String enrollmentNumber);
    boolean existsByDrivingLesson(DrivingLesson drivingLesson);
}