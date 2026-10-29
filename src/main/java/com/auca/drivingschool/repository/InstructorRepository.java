package com.auca.drivingschool.repository;

import com.auca.drivingschool.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface InstructorRepository extends JpaRepository<Instructor, UUID> {

    @Query("SELECT DISTINCT i FROM Instructor i JOIN i.licenceCategories c WHERE LOWER(c.name) = LOWER(:name)")
    List<Instructor> findByLicenceCategoryName(@Param("name") String name);
}