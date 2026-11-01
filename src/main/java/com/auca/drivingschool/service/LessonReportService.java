package com.auca.drivingschool.service;

import com.auca.drivingschool.model.DrivingLesson;
import com.auca.drivingschool.model.LessonReport;
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
public class LessonReportService {
    @Autowired
    private LessonReportRepository lessonReportRepository;
    @Autowired
    private DrivingLessonRepository drivingLessonRepository;
    public String saveLessonReport(UUID drivingLessonId, LessonReport lessonReport) {
        Optional<DrivingLesson> lessonOpt = drivingLessonRepository.findById(drivingLessonId);
        if (lessonOpt.isEmpty()) {
            return "The driving lesson with that id does not exist";
        }
        DrivingLesson lesson = lessonOpt.get();
        if (lesson.getStatus() != LessonStatus.FINISHED) {
            return "Lesson reports are only for FINISHED driving lessons";
        }
        lessonReport.setRecordedDate(LocalDate.now());
        lessonReport.setDrivingLesson(lesson);
        lessonReportRepository.save(lessonReport);
        return "Lesson report saved successfully";
    }
    public List<LessonReport> getAllLessonReports() {
        return lessonReportRepository.findAll();
    }
    public List<LessonReport> getReportsOfLearner(String enrollmentNumber) {
        return lessonReportRepository.findByLearnerEnrollmentNumber(enrollmentNumber);
    }
    public String deleteLessonReport(UUID id) {
        if (!lessonReportRepository.existsById(id)) {
            return "The lesson report with that id does not exist";
        }
        lessonReportRepository.deleteById(id);
        return "Lesson report deleted successfully";
    }
}
