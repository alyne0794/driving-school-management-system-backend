package com.auca.drivingschool.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "lesson_report")
public class LessonReport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String skillCovered;
    private String remarks;
    private int score;
    private LocalDate recordedDate;

    @ManyToOne
    @JoinColumn(name = "driving_lesson_id")
    private DrivingLesson drivingLesson;
    public LessonReport() {}
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getSkillCovered() { return skillCovered; }
    public void setSkillCovered(String skillCovered) { this.skillCovered = skillCovered; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public LocalDate getRecordedDate() { return recordedDate; }
    public void setRecordedDate(LocalDate recordedDate) { this.recordedDate = recordedDate; }
    public DrivingLesson getDrivingLesson() { return drivingLesson; }
    public void setDrivingLesson(DrivingLesson drivingLesson) { this.drivingLesson = drivingLesson; }
}