package com.auca.drivingschool.service;

import com.auca.drivingschool.model.DrivingLesson;
import com.auca.drivingschool.model.LessonStatus;
import com.auca.drivingschool.repository.DrivingLessonRepository;
import com.auca.drivingschool.repository.LessonReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DrivingLessonService {

    @Autowired
    private DrivingLessonRepository drivingLessonRepository;

    @Autowired
    private LessonReportRepository lessonReportRepository;

    public String saveDrivingLesson(DrivingLesson drivingLesson) {
        if (drivingLesson.getLessonDate() != null && drivingLesson.getLessonDate().isBefore(LocalDate.now())) {
            return "Driving lesson date cannot be in the past";
        }
        if (drivingLesson.getInstructor() != null
                && drivingLessonRepository.existsByInstructorIdAndLessonDateAndStatusNot(
                        drivingLesson.getInstructor().getId(),
                        drivingLesson.getLessonDate(),
                        LessonStatus.CANCELLED)) {
            return "Instructor is already booked on that date";
        }
        drivingLesson.setStatus(LessonStatus.BOOKED);
        drivingLessonRepository.save(drivingLesson);
        return "Driving lesson saved successfully";
    }

    public List<DrivingLesson> getAllDrivingLessons() {
        return drivingLessonRepository.findAll();
    }

    public Optional<DrivingLesson> getDrivingLessonById(UUID id) {
        return drivingLessonRepository.findById(id);
    }

    public String updateDrivingLesson(UUID id, DrivingLesson newLesson) {
        Optional<DrivingLesson> existing = drivingLessonRepository.findById(id);
        if (existing.isEmpty()) {
            return "The driving lesson with that id does not exist";
        }
        DrivingLesson lesson = existing.get();
        lesson.setLessonDate(newLesson.getLessonDate());
        lesson.setLessonType(newLesson.getLessonType());
        lesson.setDurationMinutes(newLesson.getDurationMinutes());
        lesson.setLearner(newLesson.getLearner());
        lesson.setInstructor(newLesson.getInstructor());
        drivingLessonRepository.save(lesson);
        return "Driving lesson updated successfully";
    }
    public String updateStatus(UUID id, LessonStatus newStatus) {
        Optional<DrivingLesson> existing = drivingLessonRepository.findById(id);
        if (existing.isEmpty()) {
            return "The driving lesson with that id does not exist";
        }
        DrivingLesson lesson = existing.get();
        LessonStatus current = lesson.getStatus();

        boolean allowed = false;
        if (current == LessonStatus.BOOKED
                && (newStatus == LessonStatus.ONGOING || newStatus == LessonStatus.CANCELLED)) {
            allowed = true;
        } else if (current == LessonStatus.ONGOING
                && (newStatus == LessonStatus.FINISHED || newStatus == LessonStatus.CANCELLED)) {
            allowed = true;
        }

        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        lesson.setStatus(newStatus);
        drivingLessonRepository.save(lesson);
        return "Driving lesson status updated successfully";
    }
    public String deleteDrivingLesson(UUID id) {
        Optional<DrivingLesson> existing = drivingLessonRepository.findById(id);
        if (existing.isEmpty()) {
            return "The driving lesson with that id does not exist";
        }
        if (lessonReportRepository.existsByDrivingLesson(existing.get())) {
            return "That driving lesson has lesson reports, delete them first";
        }
        drivingLessonRepository.deleteById(id);
        return "Driving lesson deleted successfully";
    }
    public List<DrivingLesson> getDrivingLessonsByStatus(LessonStatus status) {
        return drivingLessonRepository.findByStatusOrderByLessonDateAsc(status);
    }
    public List<DrivingLesson> getDrivingLessonsBetween(LocalDate start, LocalDate end) {
        return drivingLessonRepository.findByLessonDateBetweenOrderByLessonDateAsc(start, end);
    }
}