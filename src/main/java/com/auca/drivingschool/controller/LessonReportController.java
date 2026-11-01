
package com.auca.drivingschool.controller;

import com.auca.drivingschool.model.LessonReport;
import com.auca.drivingschool.service.LessonReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lesson-reports")
public class LessonReportController {

    @Autowired
    private LessonReportService lessonReportService;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID drivingLessonId, @RequestBody LessonReport lessonReport) {
        String message = lessonReportService.saveLessonReport(drivingLessonId, lessonReport);
        if (message.equals("Lesson report saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("Lesson reports are only for FINISHED driving lessons")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } 
        else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<LessonReport>> getAll() {
        return new ResponseEntity<>(lessonReportService.getAllLessonReports(), HttpStatus.OK);
    }
    @GetMapping("/learner/{enrollmentNumber}")
    public ResponseEntity<List<LessonReport>> getByLearner(@PathVariable String enrollmentNumber) {
        return new ResponseEntity<>(lessonReportService.getReportsOfLearner(enrollmentNumber), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = lessonReportService.deleteLessonReport(id);
        if (message.equals("Lesson report deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
         else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}