package com.example.project.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTransport(
        @NotNull(message = "error.invalid.number.not_null") String number
) {
}
