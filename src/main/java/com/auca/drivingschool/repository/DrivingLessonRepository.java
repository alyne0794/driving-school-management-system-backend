package com.auca.drivingschool.repository;

import com.auca.drivingschool.model.DrivingLesson;
import com.auca.drivingschool.model.LessonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DrivingLessonRepository extends JpaRepository<DrivingLesson, UUID> {

    List<DrivingLesson> findByStatusOrderByLessonDateAsc(LessonStatus status);

    List<DrivingLesson> findByLessonDateBetweenOrderByLessonDateAsc(LocalDate start, LocalDate end);

    boolean existsByInstructorIdAndLessonDateAndStatusNot(UUID instructorId, LocalDate lessonDate, LessonStatus status);

    @Query("SELECT d.status, COUNT(d) FROM DrivingLesson d GROUP BY d.status")
    List<Object[]> countLessonsGroupedByStatus();

    @Modifying
    @Query("UPDATE DrivingLesson d SET d.status = com.auca.drivingschool.model.LessonStatus.CANCELLED WHERE d.instructor.id = :instructorId AND d.lessonDate = :date AND d.status <> com.auca.drivingschool.model.LessonStatus.FINISHED")
    int cancelDay(@Param("instructorId") UUID instructorId, @Param("date") LocalDate date);

    long countByLessonDate(LocalDate lessonDate);

    long countByStatus(LessonStatus status);
}