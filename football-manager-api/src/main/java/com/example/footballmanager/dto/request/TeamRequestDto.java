package com.example.footballmanager.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TeamRequestDto(
        @NotBlank(message = "value is required and can't consist only of whitespace characters")
        String name,
        @NotBlank(message = "value is required and can't consist only of whitespace characters")
        String country,
        @NotBlank(message = "value is required and can't consist only of whitespace characters")
        String city,
        @NotNull(message = "value is required")
        @Min(value = 0, message = "value can`t be negative")
        BigDecimal balance,
        @NotNull(message = "value is required")
        @Min(value = 0, message = "value should be from 0 to 10.0")
        @Max(value = 10, message = "value should be from 0 to 10.0")
        Float fee
) {}
