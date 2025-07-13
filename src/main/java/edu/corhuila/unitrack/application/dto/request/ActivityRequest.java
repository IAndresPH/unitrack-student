package edu.corhuila.unitrack.application.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.*;

public record ActivityRequest(
    @NotBlank(message = ACTIVITY_NAME_REQUIRED)
    @Size(min = 1, max = 30, message = ACTIVITY_NAME_SIZE)
    String name,

    @NotNull(message = ACTIVITY_PERCENTAGE_REQUIRED)
    @DecimalMin(value = "0.1", message = ACTIVITY_PERCENTAGE_RANGE)
    @DecimalMax(value = "100.0", message = ACTIVITY_PERCENTAGE_RANGE)
    Double percentage,

    @NotNull(message = CUT_ID_REQUIRED)
    Long cutId,

    @NotNull(message = SUBJECT_ID_REQUIRED)
    Long subjectId
) {}