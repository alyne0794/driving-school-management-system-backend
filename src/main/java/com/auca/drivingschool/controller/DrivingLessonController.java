package com.auca.drivingschool.controller;

import com.auca.drivingschool.model.DrivingLesson;
import com.auca.drivingschool.model.LessonStatus;
import com.auca.drivingschool.service.DrivingLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/driving-lessons")
public class DrivingLessonController {

    @Autowired
    private DrivingLessonService drivingLessonService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody DrivingLesson drivingLesson) {
        String message = drivingLessonService.saveDrivingLesson(drivingLesson);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DrivingLesson>> getAll() {
        return new ResponseEntity<>(drivingLessonService.getAllDrivingLessons(), HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<DrivingLesson>> getByStatus(@RequestParam LessonStatus status) {
        return new ResponseEntity<>(drivingLessonService.getDrivingLessonsByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<DrivingLesson>> getBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(drivingLessonService.getDrivingLessonsBetween(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<DrivingLesson> lesson = drivingLessonService.getDrivingLessonById(id);
        if (lesson.isPresent()) {
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The driving lesson with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody DrivingLesson drivingLesson) {
        String message = drivingLessonService.updateDrivingLesson(id, drivingLesson);
        if (message.equals("Driving lesson updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = drivingLessonService.deleteDrivingLesson(id);
        if (message.equals("Driving lesson deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}