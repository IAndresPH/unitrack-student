package edu.corhuila.unitrack.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentRequest(
   @NotBlank
   @Size
   String firstName,

   @NotBlank
   @Size
   String lastName,

   @NotBlank
   @Size
   @Email
   String email,

   @NotBlank
   @Size
   String studentCode,

   @NotBlank
   @Size
   String program,

   @NotNull
   @Min(value = 1)
   @Max(value = 10)
   Integer semester
) {}