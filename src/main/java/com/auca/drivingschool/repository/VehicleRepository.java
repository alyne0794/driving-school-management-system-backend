package com.auca.drivingschool.repository;

import com.auca.drivingschool.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    @Query("SELECT v FROM Vehicle v WHERE NOT EXISTS (SELECT i FROM Instructor i WHERE i.vehicle = v) ORDER BY v.fleetNumber")
    List<Vehicle> findFreeVehicles();
}