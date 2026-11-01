package com.auca.drivingschool.service;

import com.auca.drivingschool.model.LessonStatus;
import com.auca.drivingschool.repository.DrivingLessonRepository;
import com.auca.drivingschool.repository.InstructorRepository;
import com.auca.drivingschool.repository.LearnerRepository;
import com.auca.drivingschool.repository.LessonReportRepository;
import com.auca.drivingschool.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DrivingLessonRepository drivingLessonRepository;

    @Autowired
    private LessonReportRepository lessonReportRepository;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("learners", learnerRepository.count());
        summary.put("instructors", instructorRepository.count());
        summary.put("freeVehicles", (long) vehicleRepository.findFreeVehicles().size());
        summary.put("lessonsToday", drivingLessonRepository.countByLessonDate(LocalDate.now()));
        summary.put("booked", drivingLessonRepository.countByStatus(LessonStatus.BOOKED));
        summary.put("lessonReports", lessonReportRepository.count());
        return summary;
    }
}