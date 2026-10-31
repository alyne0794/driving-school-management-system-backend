package com.auca.drivingschool.service;

import com.auca.drivingschool.model.Instructor;
import com.auca.drivingschool.model.LicenceCategory;
import com.auca.drivingschool.model.Vehicle;
import com.auca.drivingschool.repository.InstructorRepository;
import com.auca.drivingschool.repository.LicenceCategoryRepository;
import com.auca.drivingschool.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private LicenceCategoryRepository licenceCategoryRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public String saveInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
        return "Instructor saved successfully";
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(UUID id) {
        return instructorRepository.findById(id);
    }

    public String updateInstructor(UUID id, Instructor newInstructor) {
        Optional<Instructor> existing = instructorRepository.findById(id);
        if (existing.isEmpty()) {
            return "The instructor with that id does not exist";
        }
        Instructor instructor = existing.get();
        instructor.setFirstName(newInstructor.getFirstName());
        instructor.setLastName(newInstructor.getLastName());
        instructor.setLicenseNumber(newInstructor.getLicenseNumber());
        instructor.setPhone(newInstructor.getPhone());
        instructor.setVehicle(newInstructor.getVehicle());
        if (newInstructor.getLicenceCategories() != null && !newInstructor.getLicenceCategories().isEmpty()) {
            instructor.setLicenceCategories(newInstructor.getLicenceCategories());
        }
        instructorRepository.save(instructor);
        return "Instructor updated successfully";
    }

    public String deleteInstructor(UUID id) {
        if (!instructorRepository.existsById(id)) {
            return "The instructor with that id does not exist";
        }
        instructorRepository.deleteById(id);
        return "Instructor deleted successfully";
    }

    public List<Instructor> getInstructorsByLicenceCategory(String name) {
        return instructorRepository.findByLicenceCategoryName(name);
    }

    public String addLicenceCategory(UUID instructorId, UUID licenceCategoryId) {
        Optional<Instructor> instructorOpt = instructorRepository.findById(instructorId);
        if (instructorOpt.isEmpty()) {
            return "The instructor with that id does not exist";
        }
        Optional<LicenceCategory> categoryOpt = licenceCategoryRepository.findById(licenceCategoryId);
        if (categoryOpt.isEmpty()) {
            return "That licence category does not exist";
        }
        Instructor instructor = instructorOpt.get();
        LicenceCategory category = categoryOpt.get();

        if (instructor.getLicenceCategories().contains(category)) {
            return "The instructor already has that licence category";
        }
        instructor.getLicenceCategories().add(category);
        instructorRepository.save(instructor);
        return "Licence category added to the instructor";
    }

    public String changeVehicle(UUID instructorId, int fleetNumber) {
        Optional<Instructor> instructorOpt = instructorRepository.findById(instructorId);
        if (instructorOpt.isEmpty()) {
            return "The instructor with that id does not exist";
        }
        Optional<Vehicle> vehicleOpt = vehicleRepository.findByFleetNumber(fleetNumber);
        if (vehicleOpt.isEmpty()) {
            return "The vehicle with that fleetNumber does not exist";
        }
        Instructor instructor = instructorOpt.get();
        Vehicle vehicle = vehicleOpt.get();

        boolean alreadyHisVehicle = instructor.getVehicle() != null
                && instructor.getVehicle().getId().equals(vehicle.getId());

        if (!alreadyHisVehicle && instructorRepository.existsByVehicle(vehicle)) {
            return "That vehicle is taken by another instructor";
        }
        instructor.setVehicle(vehicle);
        instructorRepository.save(instructor);
        return "Vehicle changed successfully";
    }
}