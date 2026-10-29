package com.auca.drivingschool.controller;

import com.auca.drivingschool.model.Instructor;
import com.auca.drivingschool.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Instructor instructor) {
        String message = instructorService.saveInstructor(instructor);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Instructor>> getAll() {
        return new ResponseEntity<>(instructorService.getAllInstructors(), HttpStatus.OK);
    }

    @GetMapping("/by-licence-category")
    public ResponseEntity<List<Instructor>> getByLicenceCategory(@RequestParam String name) {
        return new ResponseEntity<>(instructorService.getInstructorsByLicenceCategory(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Instructor> instructor = instructorService.getInstructorById(id);
        if (instructor.isPresent()) {
            return new ResponseEntity<>(instructor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The instructor with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Instructor instructor) {
        String message = instructorService.updateInstructor(id, instructor);
        if (message.equals("Instructor updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = instructorService.deleteInstructor(id);
        if (message.equals("Instructor deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}