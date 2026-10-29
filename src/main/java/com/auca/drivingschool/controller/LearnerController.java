package com.auca.drivingschool.controller;

import com.auca.drivingschool.model.Learner;
import com.auca.drivingschool.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/learners")
public class LearnerController {

    @Autowired
    private LearnerService learnerService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Learner learner) {
        String message = learnerService.saveLearner(learner);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Learner>> getAll() {
        return new ResponseEntity<>(learnerService.getAllLearners(), HttpStatus.OK);
    }
     @GetMapping("/by-last-name")
    public ResponseEntity<List<Learner>> getByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(learnerService.getLearnersByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Learner> learner = learnerService.getLearnerById(id);
        if (learner.isPresent()) {
            return new ResponseEntity<>(learner.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The learner with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Learner learner) {
        String message = learnerService.updateLearner(id, learner);
        if (message.equals("Learner updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = learnerService.deleteLearner(id);
        if (message.equals("Learner deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
