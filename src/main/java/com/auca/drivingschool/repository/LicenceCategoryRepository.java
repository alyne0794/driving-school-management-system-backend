package com.auca.drivingschool.repository;

import com.auca.drivingschool.model.LicenceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface LicenceCategoryRepository extends JpaRepository<LicenceCategory, UUID> {

    @Query("SELECT c FROM LicenceCategory c WHERE c.instructors IS EMPTY")
    List<LicenceCategory> findUnusedLicenceCategories();
}