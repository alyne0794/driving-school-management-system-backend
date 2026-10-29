package com.auca.drivingschool.service;

import com.auca.drivingschool.model.Instructor;
import com.auca.drivingschool.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

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
}