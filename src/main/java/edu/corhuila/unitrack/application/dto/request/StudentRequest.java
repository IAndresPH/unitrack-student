package edu.corhuila.unitrack.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.*;

public record StudentRequest(
   @NotBlank(message = FIRST_NAME_REQUIRED)
   @Size(min = 1, max = 30, message = NAME_SIZE)
   String firstName,

   @NotBlank(message = LAST_NAME_REQUIRED)
   @Size(min = 1, max = 30, message = LAST_NAME_SIZE)
   String lastName,

   @NotBlank(message = EMAIL_REQUIRED)
   @Email(message = EMAIL_INVALID)
   @Size(max = 50, message = EMAIL_SIZE)
   String email,

   @NotBlank(message = STUDENT_CODE_REQUIRED)
   @Size(max = 20, message = STUDENT_CODE_SIZE)
   String studentCode,

   @NotBlank(message = PROGRAM_REQUIRED)
   @Size(max = 30, message = PROGRAM_SIZE)
   String program,

   @NotNull(message = SEMESTER_REQUIRED)
   @Min(value = 1, message = SEMESTER_RANGE)
   @Max(value = 10, message = SEMESTER_RANGE)
   Integer semester
) {}