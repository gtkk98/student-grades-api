package com.thilina.student_grades_api.service;

import com.thilina.student_grades_api.dto.EnrollmentDto.GpaResponse;
import com.thilina.student_grades_api.dto.StudentDto.StudentRequest;
import com.thilina.student_grades_api.dto.StudentDto.StudentResponse;
import com.thilina.student_grades_api.entity.Student;
import com.thilina.student_grades_api.repository.EnrollmentRepository;
import com.thilina.student_grades_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public StudentResponse createStudent(StudentRequest req) {
        if (studentRepository.existsByEmail(req.email())) {
            throw new DuplicateResourceException(
                    "Email already taken: " + req.email()
            );
        }
        Student student = Student.builder()
                .firstName(req.firstName())
                .lastName(req.lastName())
                .email(req.email())
                .build();
        return toResponse(studentRepository.save(student));
    }

    @Transactional(readOnly = true)
    public GpaResponse getStudentGpa(Long studentId) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found: " + studentId
                ));

        Double gpa = studentRepository
                .calculateGpaByStudentId(studentId);

        Double rounded = (gpa != null)
                ? Math.round(gpa * 100.0) / 100.0
                : null;

        int count = enrollmentRepository
                .findByStudentIdWithCourse(studentId).size();

        return new GpaResponse(
                s.getId(),
                s.getFirstName() + " " + s.getLastName(),
                rounded,
                count
        );
    }

    private StudentResponse toResponse(Student s) {
        return new StudentResponse(
                s.getId(), s.getFirstName(),
                s.getLastName(), s.getEmail()
        );
    }
}
