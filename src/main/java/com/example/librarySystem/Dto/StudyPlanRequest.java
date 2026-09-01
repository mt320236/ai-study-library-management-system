package com.example.librarySystem.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudyPlanRequest {
    private Long studentId;
    @NotBlank(message = "subjects is required")
    private String subjects;
    @Min(value = 1,message = "days must be atleast 1")
    @Max(value = 180, message = "days must be at most 180")
    private int days;
    @Min(value = 1, message = "hoursPerDay must be at least 1")
    @Max(value = 16, message = "hoursPerDay must be at most 16")
    private int hoursPerDay;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }
}
