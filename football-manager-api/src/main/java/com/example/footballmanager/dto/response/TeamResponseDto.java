package com.example.footballmanager.dto.response;

import java.math.BigDecimal;

public record TeamResponseDto(
        Long id,
        String name,
        String country,
        String city,
        BigDecimal balance,
        Float fee
) {}
