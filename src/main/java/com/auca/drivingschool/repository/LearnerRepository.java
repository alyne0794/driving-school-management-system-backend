package com.auca.drivingschool.repository;

import com.auca.drivingschool.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LearnerRepository extends JpaRepository<Learner, UUID> {
}
