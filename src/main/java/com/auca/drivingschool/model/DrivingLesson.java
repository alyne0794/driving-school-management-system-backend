package com.auca.drivingschool.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "driving_lesson")
public class DrivingLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate lessonDate;

    @Enumerated(EnumType.STRING)
    private LessonStatus status;

    private String lessonType;
    private int durationMinutes;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public DrivingLesson() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDate getLessonDate() { return lessonDate; }
    public void setLessonDate(LocalDate lessonDate) { this.lessonDate = lessonDate; }

    public LessonStatus getStatus() { return status; }
    public void setStatus(LessonStatus status) { this.status = status; }

    public String getLessonType() { return lessonType; }
    public void setLessonType(String lessonType) { this.lessonType = lessonType; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public Learner getLearner() { return learner; }
    public void setLearner(Learner learner) { this.learner = learner; }

    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
}