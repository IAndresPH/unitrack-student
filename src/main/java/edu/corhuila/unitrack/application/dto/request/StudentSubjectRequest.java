package edu.corhuila.unitrack.application.dto.request;

import jakarta.validation.constraints.NotNull;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.STUDENT_ID_REQUIRED;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.SUBJECT_ID_REQUIRED;

public record StudentSubjectRequest(
    @NotNull(message = STUDENT_ID_REQUIRED)
    Long studentId,

    @NotNull(message = SUBJECT_ID_REQUIRED)
    Long subjectId
) {}