package com.thilina.student_grades_api.dto;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class EnrollmentDto {

    public record EnrollRequest(
            @NotNull
            Long studentId,

            @NotNull
            Long courseId,

            @NotNull
            String semester

    ) {}

    public record GradeRequest(
            @NotNull
            @DecimalMin("0.0")
            @DecimalMax("4.0")
            Double grade
    ) {}

    // Student's view of their courses
    public record EnrollmentResponse(
            Long enrollmentId,
            Long courseId,
            String courseName,
            String courseCode,
            Integer credits,
            Double grade,
            String semester
    ) {}

    // GPA summary for student
    public record GpaResponse(
            Long studentId,
            String studentName,
            Double gpa,
            int enrolledCourses
    ) {}
}
