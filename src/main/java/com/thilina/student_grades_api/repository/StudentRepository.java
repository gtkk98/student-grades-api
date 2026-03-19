package com.thilina.student_grades_api.repository;

import com.thilina.student_grades_api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // SELECT COUNT(*) > 0 WHERE email = ?
    boolean existsByEmail(String email);

    // SELECT * FROM WHERE email =?
    Optional<Student>  findByEmail(String email);

    // Custom query weighted GPA calculation
    @Query("""
           SELECT SUM(e.grade * c.credits) 
                      / SUM(e.course.credits)
           FROM Enrollment e
           WHERE e.student.id = :studentId
           AND e.grade IS NOT NULL
           """)
    Double calculateGpaByStudentId(
            @Param("studentId") Long studentId
    );
}
