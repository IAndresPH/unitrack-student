package edu.corhuila.unitrack.application.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.*;

public record StudentActivityRequest(
    @NotNull(message = STUDENT_ID_REQUIRED)
    Long studentId,

    @NotNull(message = ACTIVITY_ID_INVALID)
    Long activityId,

    @NotNull(message = ACTIVITY_GRADE_REQUIRED)
    @DecimalMin(value = "0.0", message = ACTIVITY_GRADE_RANGE)
    @DecimalMax(value = "5.0", message = ACTIVITY_GRADE_RANGE)
    Double grade
) {}