package com.thilina.student_grades_api.repository;

import com.thilina.student_grades_api.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Check duplicate before enrolling
    boolean existsByStudentIdAndCourseId(
            Long studentId, Long courseId
    );

    // Load enrollment with course in one SQL Query
    @Query("""
            SELECT e FROM Enrollment e
            JOIN FETCH e.course
            WHERE e.student.id = :studentId
            ORDER BY e.semester DESC
            """)
    List<Enrollment> findByStudentIdWithCourse(
            @Param("studentId") Long studentId
    );

    // Find one enrollment
    Optional<Enrollment> findByStudentIdAndCourseId(
            Long studentId, Long courseId
    );

    // Find Who is in this course?
    @Query("""
          SELECT e FROM Enrollment e
          JOIN FETCH e.student
          WHERE e.course_id = :courseId
          """)
    List<Enrollment> findByCourseIdWithStudent(
            @Param("courseId") Long courseId
    );

}
