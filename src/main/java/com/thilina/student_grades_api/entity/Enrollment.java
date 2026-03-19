package com.thilina.student_grades_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enrollments",
            // Prevent duplicate enrollment at DB level
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"student_id", "course_id"}
        )
    )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many enrollment one student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Many enrollment one course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private Double grade;

    private String semester;

}
