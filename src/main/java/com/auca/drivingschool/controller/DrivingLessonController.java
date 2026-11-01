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
        if (message.equals("Driving lesson saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("Driving lesson date cannot be in the past")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
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

    @GetMapping("/stats/by-status")
    public ResponseEntity<List<Object[]>> getStatsByStatus() {
        return new ResponseEntity<>(drivingLessonService.getStatsByStatus(), HttpStatus.OK);
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

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable UUID id, @RequestParam LessonStatus status) {
        String message = drivingLessonService.updateStatus(id, status);
        if (message.equals("Driving lesson status updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.startsWith("Cannot change status")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelDay(@RequestParam UUID instructorId, @RequestParam String date) {
        LocalDate day = LocalDate.parse(date);
        String message = drivingLessonService.cancelDay(instructorId, day);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = drivingLessonService.deleteDrivingLesson(id);
        if (message.equals("Driving lesson deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("That driving lesson has lesson reports, delete them first")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}