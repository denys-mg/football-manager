package com.example.footballmanager.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record PlayerRequestDto(
        @NotBlank(message = "value is required and can't consist only of whitespace characters")
        String firstname,
        @NotBlank(message = "value is required and can't consist only of whitespace characters")
        String lastname,
        @NotNull(message = "value is required")
        @Positive(message = "value should be from 1 to 100")
        @Max(value = 100, message = "value should be from 1 to 100")
        Integer age,
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate careerStartDate,
        @Positive(message = "value can't be negative or 0")
        Long teamId
) {}
