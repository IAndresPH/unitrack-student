package edu.corhuila.unitrack.application.dto.request;

import static edu.corhuila.unitrack.application.shared.constants.ValidationMessages.*;
import java.util.List;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CutRequest(
    @NotBlank(message = CUT_NAME_REQUIRED)
    @Size(min = 1, max = 30, message = NAME_SIZE)
    String name,

    @NotNull(message = CUT_PERCENTAGE_REQUIRED)
    @DecimalMin(value = "0.1", message = CUT_PERCENTAGE_RANGE)
    @DecimalMax(value = "100.0", message = CUT_PERCENTAGE_RANGE)
    Double percentage,

    @NotNull(message = SUBJECT_LIST_REQUIRED)
    List<@NotNull(message = SUBJECT_ID_INVALID) Long> subjectIds
) {}