package com.auca.drivingschool.repository;

import com.auca.drivingschool.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface LearnerRepository extends JpaRepository<Learner, UUID> {

    List<Learner> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT d.learner FROM DrivingLesson d WHERE d.instructor.id = :instructorId")
    List<Learner> findLearnersOfInstructor(@Param("instructorId") UUID instructorId);
}