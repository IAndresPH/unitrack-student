package edu.corhuila.unitrack.application.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.STUDENT_ID_REQUIRED;

public record EnrollmentRequest(
    @NotNull(message = STUDENT_ID_REQUIRED)
    Long studentId,

    @NotNull(message = "La lista de materias es obligatoria")
    List<Long> subjectIds,

    @NotNull(message = "El semestre es obligatorio")
    Integer semester
) {}