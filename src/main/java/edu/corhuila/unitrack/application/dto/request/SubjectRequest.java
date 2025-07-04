package edu.corhuila.unitrack.application.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SubjectRequest(
    @NotBlank
    @Size
    String name,

    @NotNull
    @Min(value = 1)
    @Max(value = 13)
    Integer credit,

    @NotNull
    Long studentId
) {}