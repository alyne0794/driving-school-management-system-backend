package com.auca.drivingschool.service;

import com.auca.drivingschool.model.DrivingLesson;
import com.auca.drivingschool.repository.DrivingLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DrivingLessonService {

    @Autowired
    private DrivingLessonRepository drivingLessonRepository;

    public String saveDrivingLesson(DrivingLesson drivingLesson) {
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
        lesson.setStatus(newLesson.getStatus());
        lesson.setLessonType(newLesson.getLessonType());
        lesson.setDurationMinutes(newLesson.getDurationMinutes());
        lesson.setLearner(newLesson.getLearner());
        lesson.setInstructor(newLesson.getInstructor());
        drivingLessonRepository.save(lesson);
        return "Driving lesson updated successfully";
    }

    public String deleteDrivingLesson(UUID id) {
        if (!drivingLessonRepository.existsById(id)) {
            return "The driving lesson with that id does not exist";
        }
        drivingLessonRepository.deleteById(id);
        return "Driving lesson deleted successfully";
    }
}