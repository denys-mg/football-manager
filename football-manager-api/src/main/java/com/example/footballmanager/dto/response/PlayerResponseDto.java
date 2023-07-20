package com.example.footballmanager.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record PlayerResponseDto(
        Long id,
        String firstname,
        String lastname,
        Integer age,
        @JsonFormat(pattern = "dd.MM.yyyy")
        LocalDate careerStartDate,
        Long teamId
) {}
