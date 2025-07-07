package edu.corhuila.unitrack.application.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.*;

public record SubjectRequest(
    @NotBlank(message = SUBJECT_NAME_REQUIRED)
    @Size(min = 1, max = 30, message = NAME_SIZE)
    String name,

    @NotNull(message = CREDIT_REQUIRED)
    @Min(value = 1, message = CREDIT_RANGE)
    @Max(value = 13, message = CREDIT_RANGE)
    Integer credit,

    @NotNull(message = STUDENT_ID_REQUIRED)
    Long studentId
) {}