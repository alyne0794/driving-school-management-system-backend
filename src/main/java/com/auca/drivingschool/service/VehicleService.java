package com.auca.drivingschool.service;

import com.auca.drivingschool.model.Vehicle;
import com.auca.drivingschool.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public String saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return "Vehicle saved successfully";
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(UUID id) {
        return vehicleRepository.findById(id);
    }

    public String updateVehicle(UUID id, Vehicle newVehicle) {
        Optional<Vehicle> existing = vehicleRepository.findById(id);
        if (existing.isEmpty()) {
            return "The vehicle with that id does not exist";
        }
        Vehicle vehicle = existing.get();
        vehicle.setFleetNumber(newVehicle.getFleetNumber());
        vehicle.setName(newVehicle.getName());
        vehicleRepository.save(vehicle);
        return "Vehicle updated successfully";
    }

    public String deleteVehicle(UUID id) {
        if (!vehicleRepository.existsById(id)) {
            return "The vehicle with that id does not exist";
        }
        vehicleRepository.deleteById(id);
        return "Vehicle deleted successfully";
    }

    public List<Vehicle> getFreeVehicles() {
        return vehicleRepository.findFreeVehicles();
    }
}