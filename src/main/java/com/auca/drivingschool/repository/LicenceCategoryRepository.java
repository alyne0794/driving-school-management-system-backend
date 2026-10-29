package com.auca.drivingschool.repository;

import com.auca.drivingschool.model.LicenceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LicenceCategoryRepository extends JpaRepository<LicenceCategory, UUID> {
}