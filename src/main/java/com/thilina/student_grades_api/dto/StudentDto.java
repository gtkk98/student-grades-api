package com.thilina.student_grades_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class StudentDto {

    // What the client sent us (input)
    public record StudentRequest(
            @NotBlank(message = "First name is required")
            String firstName,

            @NotBlank(message = "Last name is required")
            String lastName,

            @NotBlank(message = "Email is required")
            @Email(message = "Email should be valid")
            String email
    ) {}

    // What we send back to the client (output)
    public record StudentResponse(
            Long id,
            String firstName,
            String lastName,
            String email
    ) {}
}
