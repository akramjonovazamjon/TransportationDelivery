package com.example.project.dto;

import jakarta.validation.constraints.NotNull;

public record CreateClientUser(
        @NotNull(message = "error.invalid.name.not_null") String name,
        @NotNull(message = "error.invalid.phone_number.not_null") String phoneNumber
) {
}
