package com.example.project.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.List;

public record CreateTransportItem(
        @NotNull(message = "error.invalid.departure_time.not_null") Timestamp departureTime,
        @NotNull(message = "error.invalid.departure_address.not_null") String departureAddress,
        @NotNull(message = "error.invalid.arrival_time.not_null") Timestamp arrivalTime,
        @NotNull(message = "error.invalid.arrival_address.not_null") String arrivalAddress,
        @NotNull(message = "error.invalid.delivery_id.not_null") Long deliveryId,
        @NotNull(message = "error.invalid.distance.not_null") Long distance,
        List<String> stoppedAddresses
) {
}
